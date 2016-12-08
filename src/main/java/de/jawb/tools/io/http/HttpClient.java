package de.jawb.tools.io.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import de.jawb.tools.exeption.ExceptionUtil;
import de.jawb.tools.io.http.ssl.SSLConfiguration;

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
    public HttpResponse sendRequest(HttpRequest request) {
        return sendRequest(request, null);
    }

    /**
     * @param request
     * @return
     */
    @Deprecated
    public HttpResponse sendRequest(HttpRequest request, int... expectedResponseCodes) {

        logMethodCall(request, expectedResponseCodes);

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
                        throw new RuntimeException(e);
                    }

                } else {
                    logWarnNoSslConfiguration();
                }
            }

            logSetTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setUseCaches(false);

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

        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtil.getErrorMessage(e));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
