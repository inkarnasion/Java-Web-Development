package metubev1.web.servlets;

import metubev1.domain.entities.Tube;
import metubev1.domain.models.binding.TubeCreateBindingModel;
import metubev1.domain.models.service.TubeServiceModel;
import metubev1.service.TubeService;
import metubev1.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public CreateTubeServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute("tubeCreateBindingModel");
        this.tubeService.saveTube(this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class));

        resp.sendRedirect("/tubes/details?name=" + tubeCreateBindingModel.getName());

    }
}
