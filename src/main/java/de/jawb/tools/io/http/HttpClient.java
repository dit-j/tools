package de.jawb.tools.io.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import de.jawb.tools.io.net.NetworkUtil;

/**
 * @author dit (27.11.2015)
 */
public class HttpClient {

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

    private String getData(int responseCode, HttpURLConnection connection) throws IOException {
        if (responseCode > 220) {
            return NetworkUtil.readFromStream(connection.getErrorStream());
        } else {
            return NetworkUtil.readFromStream(connection.getInputStream());
        }
    }

}
