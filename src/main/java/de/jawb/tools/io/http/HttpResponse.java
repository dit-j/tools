package de.jawb.tools.io.http;

public class HttpResponse {

    private final int    code;
    private final String msg;
    private final String data;

    public HttpResponse(int code, String msg, String data) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
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
