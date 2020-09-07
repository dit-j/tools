package de.jawb.tools.http;

import java.nio.charset.StandardCharsets;

public class HttpRequestBody {

    private final String contentType;
    private final String body;

    public HttpRequestBody(String contentType, String body) {
        this.contentType = contentType;
        this.body = body;
    }

    public byte[] getData() {
        return body.getBytes(StandardCharsets.UTF_8);
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "HttpRequestBody{" +
                "contentType='" + contentType + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
