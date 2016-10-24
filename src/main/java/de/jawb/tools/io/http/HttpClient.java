package de.jawb.tools.io.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public String sendRequest(HttpRequest request) {

        HttpURLConnection connection = null;

        try {

            String urlString = request.createUrlString();
            String query = request.createQueryData();
            URL url     = createUrl(request, query);
            connection  = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setUseCaches(false);

            //
            // HEADERS
            //
            setHeaders(request, connection);

            if ("POST".equals(request.method) || "PUT".equals(request.method)) { 

                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(0);

                try (OutputStream output = connection.getOutputStream()) {
                    output.write(query.getBytes("UTF-8"));
                }
            }

            return read(connection.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private URL createUrl(HttpRequest request, String query) throws Exception {

        URL url = null;
        boolean urlRequest = isUrlRequest(request);
        String urlString = request.url;

        //
        // users/{userid}/info
        //
        if (!request.pathparams.isEmpty()) {
            for (Entry<String, String> e : request.pathparams.entrySet()) {
                urlString = urlString.replace("{" + e.getKey() + "}", e.getValue());
            }
        }

        if (urlRequest) {
            url = new URL(query == null ? urlString : urlString + "?" + query);
        } else if ("POST".equals(request.method) || "PUT".equals(request.method)) {
            url = new URL(urlString);
        } else {
            throw new RuntimeException("unknown method " + request.method);
        }

        return url;
    }

    private void setHeaders(HttpRequest request, URLConnection connection) {
        for (Entry<String, String> e : request.headers.entrySet()) {
            connection.setRequestProperty(e.getKey(), e.getValue());
        }
    }

    private boolean isUrlRequest(HttpRequest request) {
        return "GET".equals(request.method) || "DELETE".equals(request.method);
    }

    private String read(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        Appendable response = new StringBuilder(); // new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine.trim());
        }
        in.close();

        return response.toString();
    }


    public static void main(String[] args) throws Exception {
        HttpRequest r = new HttpRequest("http://localhost:8081/v1/tours/{tour}/ratetour", "POST");
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
