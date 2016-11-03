package de.jawb.tools.io.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import de.jawb.tools.io.net.NetworkUtil;

/**
 * @author dit (27.11.2015)
 */
public class HttpClient {

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier(){

                    @Override
                    public boolean verify(String hostname,
                            javax.net.ssl.SSLSession sslSession) {
                        if (hostname.equals("localhost")) {
                            return true;
                        }
                        return false;
                    }
                });
    }


    // private final Logger _logger = LoggerFactory.getLogger(this.getClass());
    private final int TIMEOUT;

    public HttpClient() {
        TIMEOUT = 15000;
    }

    public HttpClient(int connectionTimeout) {
        TIMEOUT = connectionTimeout;
    }

    public HttpResponse sendRequest(HttpRequest request) {
        return sendRequest(request, null);
    }

    /**
     * @param request
     * @return
     */
    public HttpResponse sendRequest(HttpRequest request, int... expectedResponseCodes) {

        HttpURLConnection connection = null;

        try {
            URL url = null;
            final String urlString = request.url();
            final boolean hasBodyData = request.hasBodyData();

            if (hasBodyData) {
                url = new URL(urlString);
            } else {
                String query = request.query();
                url = new URL(query == null ? urlString : urlString + "?" + query);
            }

            connection = (HttpURLConnection) url.openConnection();

            if (connection instanceof HttpsURLConnection) {
                System.out.println("trustmanager");
                try {
                    SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, new X509TrustManager[] { new SelfSignedTrustManager() }, new SecureRandom());

                    //
                    ((HttpsURLConnection) connection).setSSLSocketFactory(sc.getSocketFactory());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }

            connection.setConnectTimeout(TIMEOUT);
            connection.setUseCaches(false);

            //
            // HEADERS
            //
            for (Entry<String, String> e : request.headers().entrySet()) {
                connection.setRequestProperty(e.getKey(), e.getValue());
            }

            if (hasBodyData) {
                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(0);
                try (OutputStream output = connection.getOutputStream()) {
                    output.write(request.bodyData());
                }
            }

            return createResponse(connection, expectedResponseCodes);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    private HttpResponse createResponse(HttpURLConnection connection, int... expectedResponseCodes) throws IOException {

        int responseCode = connection.getResponseCode();
        String message = connection.getResponseMessage();
        String data = null;
        Map<String, String> headers = NetworkUtil.getResponseHeaders(connection);

        if (expectedResponseCodes != null) {

            for (int rc : expectedResponseCodes) {
                if (rc == responseCode) {
                    data = getData(responseCode, connection);
                    break;
                }
            }

        } else {
            data = getData(responseCode, connection);
        }


        return new HttpResponse(responseCode, message, data, headers);
    }

    private String getData(int rCode, HttpURLConnection connection) throws IOException {

        if (rCode >= 300) {
            if (connection.getErrorStream() != null) {
                return NetworkUtil.read(connection.getErrorStream());
            }
        } else {
            if (connection.getInputStream() != null) {
                return NetworkUtil.read(connection.getInputStream());
            }
        }

        return null;
    }

    public static void main(String[] args) {
        //        HttpRequest get = new HttpRequest("https://localhost");
        HttpRequest get = new HttpRequest("https://www.google.com");
        HttpResponse res = new HttpClient().sendRequest(get);
        System.out.println(res);
    }

}
