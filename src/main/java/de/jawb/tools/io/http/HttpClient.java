package de.jawb.tools.io.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jawb.tools.io.net.NetworkUtil;

/**
 * @author dit (27.11.2015)
 */
public class HttpClient {

    protected final Logger _logger = LoggerFactory.getLogger(this.getClass());

    private final int      TIMEOUT;

    public HttpClient() {
        TIMEOUT = 15000;
    }

    public HttpClient(int connectionTimeout) {
        TIMEOUT = connectionTimeout;
    }

    /**
     * @param request
     * @return
     */
    public HttpResponse sendRequest(HttpRequest request) {

        HttpURLConnection connection = null;

        try {
            URL url = null;
            final String urlString      = request.url();
            final boolean hasBodyData   = request.hasBodyData();

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

            int responseCode = connection.getResponseCode();
            String message = connection.getResponseMessage();
            String data = null;

            if (responseCode > 220) {
                data = NetworkUtil.readFromStream(connection.getErrorStream());
            } else {
                data = NetworkUtil.readFromStream(connection.getInputStream());
            }

            return new HttpResponse(responseCode, message, data);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HttpRequest r = new HttpRequest(HttpRequestMethod.POST, "http://localhost:8081/v1/tours/{tour}/ratetour");
        r.addHeader("User-Agent", "mytourapp/android");
        r.addHeader("Api-Key", "570603f329e62b7a29159c86");

        r.addParameter("author", "dit");
        r.addParameter("tour", "110");
        r.addParameter("stars", "5");
        r.addParameter("comment", "Klasse!");

        r.addPathParameter("tour", 2L);

        System.out.println(r);

        HttpClient client = new HttpClient();

        long start = System.currentTimeMillis();
        long i;
        for (i = 0; i < 1; i++) {
            // long s = System.currentTimeMillis();
            try {
                System.out.println(client.sendRequest(r));
                // Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // long e = System.currentTimeMillis();
            // System.out.println(response);
            // System.out.println((e - s));
        }

        long time = System.currentTimeMillis() - start;
        long reqPerSecond = (i * 1000) / time;
        System.out.println(reqPerSecond + " req/sec.");
    }

}
