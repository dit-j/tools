/**
 *
 */
package de.jawb.tools.http;

import de.jawb.tools.security.Hash;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dit (10.03.2016)
 */
public final class HttpRequest {

    private final Map<String, String> headers = new HashMap<>();
    private final HttpRequestMethod   method;

    private       String              baseUrl;
    private       StringBuilder       urlQueryParams;
    private       HttpRequestBody     requestBody;

    private final boolean cacheable;

    public HttpRequest(String baseUrl) {
        this(HttpRequestMethod.GET, baseUrl);
    }

    public HttpRequest(String baseUrl, boolean cacheable) {
        this(HttpRequestMethod.GET, baseUrl, cacheable);
    }

    public HttpRequest(HttpRequestMethod method, String baseUrl) {
        this(method, baseUrl, method.canBeCached);
    }

    public HttpRequest(HttpRequestMethod method, String baseUrl, boolean cacheable) {
        this.method = method;
        this.baseUrl = baseUrl;
        this.cacheable = cacheable;
        addHeader("Accept-Charset", "UTF-8");
    }

    public HttpRequest addHeader(String key, Object value) {
        headers.put(key, String.valueOf(value));
        return this;
    }

    public HttpRequest addPathParameter(String pathParam, Object value) {
        String placeHolder = "{" + pathParam + "}";

        if (baseUrl.contains(pathParam)) {
            baseUrl = baseUrl.replace(placeHolder, String.valueOf(value));
            return this;
        }

        throw new IllegalArgumentException(pathParam + " not found in url: " + baseUrl);
    }

    public HttpRequest addQueryParameter(String key, Object value) {

        if (urlQueryParams == null) {
            urlQueryParams = new StringBuilder();
        } else { // if (params.length() > 0)
            urlQueryParams.append("&");
        }

        try {
            urlQueryParams.append(key).append("=").append(URLEncoder.encode(String.valueOf(value), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public HttpRequest addBody(HttpRequestBody body){
        this.requestBody = body;
        addHeader("Content-Type", body.getContentType());
        return this;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public String uid() {
        StringBuilder sb = new StringBuilder(method.toString());

//        if (!headers.isEmpty()) {
//            sb.append(":");
//            Iterator<Entry<String, String>> it = headers.entrySet().iterator();
//            while (it.hasNext()) {
//                Entry<String, String> e = it.next();
//                sb.append(e.getKey()).append("-").append(e.getValue());
//                if (it.hasNext()) {
//                    sb.append("_");
//                }
//            }
//        }

        sb.append(":").append(baseUrl);

        return Hash.SHA_160(sb.toString());
    }

    //
    // HttpClient
    //

    HttpRequestMethod method() {
        return method;
    }

    Map<String, String> headers() {
        return headers;
    }

    String url(){
        if(urlQueryParams == null){
            return baseUrl;
        }
        return baseUrl + "?" + urlQueryParams.toString();
    }

    boolean hasBodyData() {
        return (method == HttpRequestMethod.POST) && requestBody != null;
    }

    HttpRequestBody body(){
        return requestBody;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HttpRequest [method=");
        builder.append(method);
        builder.append(", baseUrl=");
        builder.append(baseUrl);
        builder.append(", urlQueryParams=");
        builder.append(urlQueryParams);
        builder.append(", headers=");
        builder.append(headers);
        builder.append(", body=");
        builder.append(requestBody);
        builder.append("]");
        return builder.toString();
    }

}
