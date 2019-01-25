import main.AppConstants;
import main.http.api_implement.Request;
import main.http.api_implement.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Demo {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        // 1.read the input
        // 1.1 read valid urls line
        List<String> validURLs = Arrays.asList(reader.readLine().split("\\s+"));
        //1.2 read request info
        String requestInfo = getRequestInfo();
        reader.close();
        Request request = new Request(requestInfo);




        // 2.check is url is valid
        String body;
        String statusLine = request.getHttpVersion();

        if (!validURLs.contains(request.getRequestUrl())) {
            statusLine += " " + AppConstants.NOT_FOUND;
            body = AppConstants.NOT_FOUND_MESSAGE;

        } else if (!request.getHeaders().containsKey(AppConstants.HEADER_NAME)) {// 3.check is request contain Authorization header
            statusLine += " " + " " + AppConstants.UNAUTHORIZED;
            body = AppConstants.UNAUTHORIZED_MESSAGE;

        } else if (request.getHeaders().containsKey(AppConstants.HEADER_NAME) && request.getBodyParameters().size() == 0) {//4. check is request method is POST and have body
            statusLine += " " + AppConstants.BAD_REQUEST;
            body = AppConstants.BAD_REQUEST_MESSAGE;

        } else {//5.Success Request
            // 6.decode authorization user
            String[] authorizationHeaderParts = request.getHeaders().get(AppConstants.HEADER_NAME).split("\\s+");
            String userName = getDecode64Text(authorizationHeaderParts[1]);
            if (request.getMethod().equals(AppConstants.METHOD)) {
                body = request.parseBodyMessage(userName);
            } else {

                body = request.parseBodyMessage(userName, request.getBodyParameters());
            }
            statusLine += " " + AppConstants.SUCCESS;
        }
        Response response = new Response(statusLine, request.getHeaders(), body);
        System.out.println(response.toString());
    }


    private static String getRequestInfo() throws IOException {
        StringBuilder lines = new StringBuilder();
        String line;
        if ((line = reader.readLine()) != null && !line.isEmpty()) {
            do {
                lines.append(line).append("\r\n");
            } while ((line = reader.readLine()) != null && !line.isEmpty());
        }
        lines.append("\r\n");

        if ((line = reader.readLine()) != null && !line.isEmpty()) {
            lines.append(line);
        }


        return lines.toString();
    }

    private static String getDecode64Text(String text) {

        byte[] byteArray = Base64.getDecoder().decode(text);
        return new String(byteArray);
    }


}
