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

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    private final static String ALL_CATS_HTML_PATH = "D:\\Java\\Java WEB 2019\\JAVA EE-exercises\\src\\main\\resources\\wies\\all-cats.html";
    private final static String ERROR_NO_PERSISTEND_CATS_HTML_PATH = "D:\\Java\\Java WEB 2019\\JAVA EE-exercises\\src\\main\\resources\\wies\\error-no-persistend-cats.html";
    private final HtmlReader htmlReader;

    @Inject
    public AllCatsServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Cat> allCats = (Map<String, Cat>) req.getSession().getAttribute("cats");
        StringBuilder httpCatNameContent = new StringBuilder();
        String htmlContent;
        if (allCats != null) {

            for (String key : allCats.keySet()) {
                httpCatNameContent.append("<h3><a href = \"/cats/profile?catName={{name}}\" > {{name}} </a ></h3 >".replace("{{name}}",key)).append("\r\n");
            }
            htmlContent = htmlReader.readHtmlFile(ALL_CATS_HTML_PATH).replace("{{catList}}", httpCatNameContent);

        } else {
            htmlContent = htmlReader.readHtmlFile(ERROR_NO_PERSISTEND_CATS_HTML_PATH);
        }

        resp.getWriter().println(htmlContent);

    }
}
