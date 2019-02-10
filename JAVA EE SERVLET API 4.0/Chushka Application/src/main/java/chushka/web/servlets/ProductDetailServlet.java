package chushka.web.servlets;


import chushka.domain.models.view.ProductDetailsVieweModel;
import chushka.service.ProductService;
import chushka.util.HtmlReader;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailServlet extends HttpServlet {

    private static final String PRODUCT_DETAILS_HTML_FILE_PATH = "D:\\Java\\Java WEB 2019\\JAVA EE SERVLET API 4.0\\src\\main\\resources\\views\\details-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public ProductDetailServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDetailsVieweModel productDetailsVieweModel = this.modelMapper
                .map((this.productService.findProductByName(req.getQueryString().split("=")[1])), ProductDetailsVieweModel.class );


        String htmlFileContent = this.htmlReader.readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH)
                .replace("{{productName}}", productDetailsVieweModel.getName())
                .replace("{{productDescription}}", productDetailsVieweModel.getDescription())
                .replace("{{productType}}", productDetailsVieweModel.getType());

        resp.getWriter().println(htmlFileContent);
    }
}
