package mate.academy.jdbcdemo.web;

import mate.academy.jdbcdemo.config.Factory;
import mate.academy.jdbcdemo.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private final static Map<Request, Controller> controllers = new HashMap<>();

    static {
        controllers.put(Request.of("/servlet/login", Request.RequestMethod.POST), Factory.getLoginController());
        controllers.put(Request.of("/servlet/login", Request.RequestMethod.GET), request -> ViewModel.of("login"));
        controllers.put(Request.of("/servlet/register", Request.RequestMethod.POST), Factory.getRegisterController());
        controllers.put(Request.of("/servlet/register", Request.RequestMethod.GET), r -> ViewModel.of("register"));
        controllers.put(Request.of("/servlet/home", Request.RequestMethod.GET), r -> ViewModel.of("home"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void sendResponse(ViewModel viewModel, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectUrl = "/WEB-INF/views/%s.jsp";
        viewModel.getModel().forEach(req::setAttribute);
        req.getRequestDispatcher(String.format(redirectUrl, viewModel.getView())).forward(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath() + req.getPathInfo();
        System.out.println("Servlet method: " + req.getMethod());
        System.out.println("Servlet path and info: " + path);
        Map<String, String[]> parameterMap = req.getParameterMap();
        Request r = Request.of(path, Request.RequestMethod.valueOf(req.getMethod()), parameterMap);
        Controller controller = controllers.get(r);
        ViewModel viewModel = controller.process(r);
        sendResponse(viewModel, req, resp);
    }
}
