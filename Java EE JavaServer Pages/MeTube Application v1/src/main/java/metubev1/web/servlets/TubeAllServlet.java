package metubev1.web.servlets;

import metubev1.domain.models.views.AllTubesViewModel;
import metubev1.service.TubeService;
import metubev1.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubeAllServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setAttribute("allTubes",this.tubeService.findAllTubes()
                                              .stream().map(t->this.modelMapper.map(t,AllTubesViewModel.class)).collect(Collectors.toList()));

     req.getRequestDispatcher("/jsp/all-tubes.jsp").forward(req,resp);

    }

}

