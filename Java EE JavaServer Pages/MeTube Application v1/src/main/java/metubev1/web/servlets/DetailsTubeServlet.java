package metubev1.web.servlets;

import metubev1.domain.models.views.TubeDetailsViewModel;
import metubev1.service.TubeService;
import metubev1.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
public class DetailsTubeServlet extends HttpServlet {

    private final TubeService tubeService;

    private final ModelMapper modelMapper;

    @Inject
    public DetailsTubeServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getQueryString().split("=")[1].replace("%20", " ");

        req.setAttribute("tubeDetailsViewModel", this.modelMapper.map(this.tubeService.findTubeByName(name), TubeDetailsViewModel.class));

        req.getRequestDispatcher("/jsp/details-tube.jsp").forward(req, resp);
    }
}
