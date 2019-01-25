package main.java.api_implement;

import main.java.api.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request implements HttpRequest {
    private String method;
    private String requestUrl;
    private String httpVersion;
    private Map<String, String> headers;
    private List<HttpCookie> cookies;
    private Map<String, String> bodyParameters;
    private boolean isResource;


    public Request(String input) {
        this.init(input);


    }

    private void init(String input) {
        this.setMethod(input.split("\\s+")[0]);
        this.setRequestUrl(input.split("\\s+")[1]);
        this.setHttpVersion(input.split("\\s+")[2]);
        this.headers = new HashMap<String, String>();
        this.bodyParameters = new HashMap<String, String>();
        this.cookies = new ArrayList<HttpCookie>();
        String[] inputParts = input.split("\r\n");

        for (int i = 1; i < inputParts.length; i++) {
            if (inputParts[i].contains(": ")) {
                String[] headerKvp = inputParts[i].split(": ");
                addHeader(headerKvp[0], headerKvp[1]);
            } else {
                if (!inputParts[i].isEmpty()) {
                    String[] bodyParts = inputParts[inputParts.length - 1].split("&");
                    for (String item : bodyParts) {
                        String[] bodyKvp = item.split("=");
                        addBodyParameter(bodyKvp[0], bodyKvp[1]);
                    }
                }

            }
        }

        if (headers.containsKey("Cookie")) {

            String[] cookiesLine = headers.get("Cookie").split(";\\s+");
            for (int i = 0; i < cookiesLine.length; i++) {
                String[] cookieParts = cookiesLine[i].split("=");
                this.addCookies(new HttpCookie(cookieParts[0], cookieParts[1]));
            }

        }


    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public Map<String, String> getBodyParameters() {

        return this.bodyParameters;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {

        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;

    }

    public String getHttpVersion() {

        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {

        this.httpVersion = httpVersion;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);

    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);

    }

    @Override
    public boolean isResource() {
        return false;
    }

    public List<HttpCookie> getCookies() {
        return this.cookies;
    }

    public void addCookies(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    public void printRequestCookies() {
        for (HttpCookie cookie : this.getCookies()) {
            System.out.println(cookie.getKey() + " < - > " + cookie.getValue());
        }

    }

}
