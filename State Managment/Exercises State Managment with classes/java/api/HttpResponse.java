package main.java.api;

import java.util.Map;

public interface HttpResponse {
    Map<String, String> getHeaders();

    int getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(int statusCode);

    void setContent(byte[] content);

    void addHeader(String header, String Value);
}
