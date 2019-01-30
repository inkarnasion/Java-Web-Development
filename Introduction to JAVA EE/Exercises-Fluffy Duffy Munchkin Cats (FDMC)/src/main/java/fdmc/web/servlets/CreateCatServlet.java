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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateCatServlet extends HttpServlet {
    private final HtmlReader htmlReader;
    private final static String CREATE_CAT_HTML_PATH = "D:\\Java\\Java WEB 2019\\JAVA EE-exercises\\src\\main\\resources\\wies\\create-cat.html";

    @Inject
    public CreateCatServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String fileContent = htmlReader.readHtmlFile(CREATE_CAT_HTML_PATH);
        writer.println(fileContent);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        //check is exist catsList
        if (req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new HashMap<String, Cat>());
        }

        Map<String, Cat> cats = ((Map<String, Cat>) req.getSession().getAttribute("cats"));
        cats.putIfAbsent(cat.getName(), cat);

        resp.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));

    }
}
