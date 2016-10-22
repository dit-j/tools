/**
 *
 */
package de.jawb.tools.io.http;

import java.util.HashMap;
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
    protected final boolean             jsonRequest;

    public HttpRequest(String url, String method) {
        this(url, method, false);
    }

    public HttpRequest(String url, String method, boolean isJSONRequest) {
        super();
        this.method = method;
        this.url = url;
        this.headers.put("Accept-Charset", "UTF-8");
        this.jsonRequest = isJSONRequest;
        if ("POST".equals(method)) {
            if (isJSONRequest) {
                this.headers.put("Content-Type", "application/json; charset=UTF-8");
            } else {
                this.headers.put("Content-Type", "application/x-www-form-urlencoded");
            }
        }
    }

    public HttpRequest addHeader(String key, Object value) {
        headers.put(key, value.toString());
        return this;
    }

    public HttpRequest addPathParameter(String pathParam, Object value){
        pathparams.put(pathParam, value.toString());
        return this;
    }

    public HttpRequest addParameter(String key, Object value) {
        if (jsonRequest) {
            parameters.put("_json_", value.toString());
        } else {
            parameters.put(key, value.toString());
        }
        return this;
    }

    public String uid(){
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        sb.append(":");
        sb.append(url);
        if(!headers.isEmpty()){
            sb.append(":");
            for(Entry<String, String> e : headers.entrySet()){
                sb.append(e.getKey()).append("-").append(e.getValue());
            }
        }
        if(!parameters.isEmpty()){
            sb.append(":");
            for(Entry<String, String> e : parameters.entrySet()){
                sb.append(e.getKey()).append("-").append(e.getValue());
            }
        }
        if(!pathparams.isEmpty()){
            sb.append(":");
            for(Entry<String, String> e : pathparams.entrySet()){
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
