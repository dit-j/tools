/**
 *
 */
package de.jawb.tools.io.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.jawb.tools.security.Hash;

/**
 * @author dit (10.03.2016)
 */
public class HttpRequest {

    protected final String              method;
    protected final String              url;
    protected final Map<String, String> headers    = new HashMap<>();
    protected final Map<String, String> parameters = new HashMap<>();
    protected final Map<String, String> pathparams = new HashMap<>();
    private String                      jsonObject;

    public HttpRequest(String url, String method) {
        super();
        this.method = method;
        this.url = url;
        this.headers.put("Accept-Charset", "UTF-8");
        if ("POST".equals(method) || "PUT".equals(method)) {
            addHeader("Content-Type", "application/x-www-form-urlencoded");
        }
    }

    public HttpRequest addHeader(String key, Object value) {
        headers.put(key, value.toString());
        return this;
    }

    public HttpRequest addPathParameter(String pathParam, Object value) {
        pathparams.put(pathParam, value.toString());
        return this;
    }

    public HttpRequest addParameter(String key, Object value) {
        parameters.put(key, value.toString());
        return this;
    }

    public HttpRequest addJsonObject(String json) {
        addHeader("Content-Type", "application/json; charset=UTF-8");
        this.jsonObject = json;
        return this;
    }

    public boolean hasRequestBody() {
        return "POST".equals(method) || "PUT".equals(method);
    }

    String createUrlString() {

        String urlString = url;

        //
        // users/{userid}/info
        //
        if (!pathparams.isEmpty()) {
            for (Entry<String, String> e : pathparams.entrySet()) {
                urlString = urlString.replace("{" + e.getKey() + "}", e.getValue());
            }
        }

        return urlString;
    }

    String createQueryData() throws UnsupportedEncodingException {

        if (jsonObject != null) {

            //
            //
            //
            return jsonObject;
        }

        Map<String, String> params = parameters;
        if (!params.isEmpty()) {

            StringBuilder sb = new StringBuilder();

            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {

                Entry<String, String> pair = it.next();

                String key = pair.getKey();
                String val = URLEncoder.encode(pair.getValue(), "utf-8");

                sb.append(key).append("=").append(val);

                if (it.hasNext())
                    sb.append("&");
            }

            return sb.toString();
        }

        return null;
    }


    public String uid() {
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        sb.append(":");
        sb.append(url);
        if (!headers.isEmpty()) {
            sb.append(":");
            for (Entry<String, String> e : headers.entrySet()) {
                sb.append(e.getKey()).append("-").append(e.getValue());
            }
        }
        if (!parameters.isEmpty()) {
            sb.append(":");
            for (Entry<String, String> e : parameters.entrySet()) {
                sb.append(e.getKey()).append("-").append(e.getValue());
            }
        }
        if (!pathparams.isEmpty()) {
            sb.append(":");
            for (Entry<String, String> e : pathparams.entrySet()) {
                sb.append(e.getKey()).append("-").append(e.getValue());
            }
        }

        return Hash.SHA_160(sb.toString());
    }

    @Override
    public String toString() {
        return "HttpRequest [\n\t" + method + " " + url + "\n\t" + headers + "\n\t" + parameters + "\n]";
    }

}
