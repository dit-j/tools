package de.jawb.tools.io.http;

import java.util.Map;

public class HttpResponse {

    private final int                 code;
    private final String              msg;
    private final String              data;
    private final Map<String, String> headers;

    public HttpResponse(int code, String msg, String data) {
        this(code, msg, data, null);
    }

    public HttpResponse(int code, String msg, String data, Map<String, String> headers) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpResponse [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }


}
