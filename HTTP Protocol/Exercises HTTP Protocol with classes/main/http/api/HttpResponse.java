package main.http.api;

import java.util.Map;

public interface HttpResponse {
    Map<String, String> getHeaders();

    String getBody();

    String getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(String statusCode);

    void setContent(byte[] content);

    void addHeader(String header, String Value);

    void setBody(String Value);
}
