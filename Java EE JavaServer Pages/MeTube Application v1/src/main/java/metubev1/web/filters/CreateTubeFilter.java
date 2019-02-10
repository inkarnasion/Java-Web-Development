package metubev1.web.filters;

import metubev1.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class CreateTubeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getMethod().equalsIgnoreCase("post")) {
            TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();

            tubeCreateBindingModel.setName(httpServletRequest.getParameter("name"));
            tubeCreateBindingModel.setDescription(httpServletRequest.getParameter("description"));
            tubeCreateBindingModel.setYouTubeLink(httpServletRequest.getParameter("YouTubeLink"));
            tubeCreateBindingModel.setUploader(httpServletRequest.getParameter("uploader"));

            httpServletRequest.setAttribute("tubeCreateBindingModel", tubeCreateBindingModel);

        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
