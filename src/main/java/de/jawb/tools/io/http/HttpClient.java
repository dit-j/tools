package de.jawb.tools.io.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import de.jawb.tools.io.http.ssl.SSLConfiguration;
import de.jawb.tools.logging.ISimpleLogger;

/**
 * @author dit (27.11.2015)
 */
public class HttpClient extends HttpClientSupport {

    protected final int        TIMEOUT;
    protected SSLConfiguration sslConfiguration;

    public HttpClient() {
        this(null, 15000);
    }

    public HttpClient(int connectionTimeout) {
        this(null, connectionTimeout);
    }

    public HttpClient(SSLConfiguration sslConfigs, int connectionTimeout) {
        TIMEOUT = connectionTimeout;
        sslConfiguration = sslConfigs;
    }

    /**
     * @param request
     * @return
     */
    public HttpResponse sendRequest(HttpRequest request) throws UnknownHostException, SocketTimeoutException {

        logMethodCall(request);

        HttpURLConnection connection = null;

        try {
            final String urlString = request.url();
            final boolean hasBodyData = request.hasBodyData();

            if (hasBodyData) {
                connection = (HttpURLConnection) new URL(urlString).openConnection();
            } else {
                String query = request.query();
                String urlSpec = query == null ? urlString : urlString + "?" + query;
                connection = (HttpURLConnection) new URL(urlSpec).openConnection();
            }

            logConnectionOpened(connection);

            //
            // SSL
            //
            if (connection instanceof HttpsURLConnection) {

                if (sslConfiguration != null) {

                    logSSLConfiguration(sslConfiguration);

                    try {

                        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
                        HostnameVerifier hostNameVerifier = sslConfiguration.getHostNameVerifier();
                        SSLSocketFactory sslSocketFactory = sslConfiguration.getSslSocketFactory();

                        if (hostNameVerifier != null) {
                            httpsURLConnection.setHostnameVerifier(hostNameVerifier);
                        }

                        if (sslSocketFactory != null) {
                            httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
                        }

                    } catch (Exception e) {
                        logErrorSSLSocketFactory(e);
                        throw e;
                    }

                } else {
                    logWarnNoSslConfiguration();
                }
            }

            logSetTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setUseCaches(false);
            connection.setRequestMethod(request.method().toString());

            //
            // HEADERS
            //
            for (Entry<String, String> e : request.headers().entrySet()) {
                connection.setRequestProperty(e.getKey(), e.getValue());
            }

            //
            // BODY
            //
            if (hasBodyData) {

                logInitBodyData();

                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(0);
                try (OutputStream output = connection.getOutputStream()) {
                    output.write(request.bodyData());
                }
            }

            //
            // READ DATA
            //
            return createResponse(connection);

        } catch (UnknownHostException e) {
            throw e;
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public final void setLogger(ISimpleLogger logger){
        setSimpleLogger(logger);
    }

    public static void main(String[] args) {

        String host = "https://api.watchdog-anti-theft.com/sdasdf".substring(8);
        int i = host.indexOf('/');
        if(i > 0){
            host = host.substring(0, i);
        }

        System.out.println('*' + host + "*");
    }

    public static void mains(String[] args) throws SocketTimeoutException, UnknownHostException {
        HttpRequest r = new HttpRequest(HttpRequestMethod.GET, "http://www.google.com/{path}");
        r.addPathParameter("path", "search").addParameter("q", "apple");

        HttpResponse response = new HttpClient(10).sendRequest(r);

        System.out.println(response);
    }

}
