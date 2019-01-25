package main;

import main.java.api_implement.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.println("HTTPCOOKIE WITH CLASS IMPL...");
        String inputRequest = getRequestInfo();
        Request request = new Request(inputRequest);
        request.printRequestCookies();
    }

    private static String getRequestInfo() throws IOException {
        StringBuilder lines = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            lines.append(line).append("\r\n");
        }
        lines.append("\r\n");

        if ((line = reader.readLine()) != null && !line.isEmpty()) {
            lines.append(line);
        }


        return lines.toString();
    }
}
