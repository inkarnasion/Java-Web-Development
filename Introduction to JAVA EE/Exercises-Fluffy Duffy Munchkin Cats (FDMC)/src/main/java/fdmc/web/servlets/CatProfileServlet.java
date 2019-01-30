package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
    private final static String CAT_PROFILE_HTML_PATH = "D:\\Java\\Java WEB 2019\\JAVA EE-exercises\\src\\main\\resources\\wies\\profile-cat.html";
    private final static String ERROR_NOT_FOUND_CAT_HTML_PATH = "D:\\Java\\Java WEB 2019\\JAVA EE-exercises\\src\\main\\resources\\wies\\error-no-exist-cat.html";
    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats")).get(req.getQueryString().split("=")[1]);
        String htmlFileContent;
        if (cat == null) {
            htmlFileContent = this.htmlReader.readHtmlFile(ERROR_NOT_FOUND_CAT_HTML_PATH).replace("{{name}}", req.getQueryString().split("=")[1]);
        } else {
            htmlFileContent = this.htmlReader.readHtmlFile(CAT_PROFILE_HTML_PATH).replace("{{name}}", cat.getName()).replace("{{breed}}", cat.getBreed()).replace("{{color}}", cat.getColor()).replace("{{age}}", cat.getAge().toString());
        }
        resp.getWriter().println(htmlFileContent);


    }
}
