/**
 *
 */
package de.jawb.tools.io.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.jawb.tools.json.JsonUtil;
import de.jawb.tools.security.Hash;

/**
 * @author dit (10.03.2016)
 */
public final class HttpRequest {

    private final Map<String, String> headers = new HashMap<>();
    private final String              method;
    private String                    url;

    private String                    jsonObject;
    private StringBuilder             query;

    public HttpRequest(String url, String method) {
        super();
        this.method = method.toUpperCase();
        this.url = url;
        addHeader("Accept-Charset", "UTF-8");
        if (hasRequestBody()) {
            addHeader("Content-Type", "application/x-www-form-urlencoded");
        }
    }

    public HttpRequest addHeader(String key, Object value) {
        headers.put(key, value.toString());
        return this;
    }

    public HttpRequest addPathParameter(String pathParam, Object value) {
        String placeHolder = "{" + pathParam + "}";
        if (url.indexOf(placeHolder) < 0) {
            throw new IllegalArgumentException(pathParam + " not found in url: " + url);
        }
        url = url.replace(placeHolder, value.toString());
        return this;
    }

    public HttpRequest addParameter(String key, Object value) {

        if (query == null) {
            query = new StringBuilder();
        } else { // if (params.length() > 0)
            query.append("&");
        }

        try {
            query.append(key).append("=").append(URLEncoder.encode(value.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public HttpRequest addObject(Object o) {
        addHeader("Content-Type", "application/json; charset=UTF-8");
        this.jsonObject = JsonUtil.toJSON(o);
        return this;
    }

    boolean hasRequestBody() {
        return "POST".equals(method) || "PUT".equals(method);
    }

    String method() {
        return method;
    }

    Map<String, String> headers() {
        return headers;
    }

    String url() {
        return url;
    }

    String query() {
        return query != null ? query.toString() : null;
    }

    byte[] requestBody() {
        if (hasRequestBody()) {
            if (jsonObject != null) {
                return jsonObject.getBytes(Charset.forName("UTF-8"));
            } else if (query != null) {
                return query().getBytes(Charset.forName("UTF-8"));
            }
        }
        return null;
    }

    public String uid() {
        StringBuilder sb = new StringBuilder(method);

        if (!headers.isEmpty()) {
            sb.append(":");
            Iterator<Entry<String, String>> it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, String> e = it.next();
                sb.append(e.getKey()).append("-").append(e.getValue());
                if (it.hasNext()) {
                    sb.append("_");
                }
            }
        }

        sb.append(":").append(url);

        if (query != null) {
            sb.append(":").append(query.toString());
        }
        
        return Hash.SHA_160(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HttpRequest [method=");
        builder.append(method);
        builder.append(", url=");
        builder.append(url);
        builder.append(", query=");
        builder.append(query);
        builder.append(", headers=");
        builder.append(headers);
        builder.append(", jsonObject=");
        builder.append(jsonObject);
        builder.append("]");
        return builder.toString();
    }

}
