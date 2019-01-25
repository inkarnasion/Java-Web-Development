package main.http.api_implement;

import main.http.api.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class Response implements HttpResponse {
    private String status;
    private Map<String, String> headers;
    private String body;

    public Response(String status, Map<String, String> headers, String body) {
        this.init(status, headers, body);
    }

    private void init(String status, Map<String, String> headers, String body) {
        this.setStatusCode(status);
        this.headers = new HashMap<String, String>();
        this.setBody(body);

        for (String headerKey : new String[]{"Date", "Host", "Content-Type"}) {
            String headerValue = headers.get(headerKey);
            if (headerValue != null) {
                this.addHeader(headerKey, headerValue);
            }
        }


    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getStatusCode() {
        return this.status;
    }

    @Override
    public byte[] getContent() {
        return new byte[0];
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setStatusCode(String statusCode) {
        this.status = statusCode;
    }

    @Override
    public void setContent(byte[] content) {

    }

    @Override
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    @Override
    public void setBody(String text) {
        this.body = text;

    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append(this.getStatusCode()).append("\r\n");
        for (String key : headers.keySet()) {
            String value = headers.get(key);
            if (value != null) {
                response.append(key + ": " + value).append("\r\n");
            }
        }
        response.append("\r\n").append(this.getBody());


        return response.toString();
    }
}
