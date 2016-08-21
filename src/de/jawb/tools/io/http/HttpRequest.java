/**
 * 
 */
package de.jawb.tools.io.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dit (10.03.2016)
 */
public class HttpRequest {
    
    protected final String              method;
    protected final String              url;
    protected final Map<String, String> headers    = new HashMap<>();
    protected final Map<String, String> parameters = new HashMap<>();
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
            if(isJSONRequest) {
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
    
    public HttpRequest addParameter(String key, Object value) {
        
        if(jsonRequest) {
            parameters.put("_json_", value.toString());
        } else {
            parameters.put(key, value.toString());
        }
        
        return this;
    }
    
    @Override
    public String toString() {
        return "HttpRequest [\n\t" + method + " " + url + "\n\t" + headers + "\n\t" + parameters + "\n]";
    }
    
}
