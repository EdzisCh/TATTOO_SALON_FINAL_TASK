package by.chebotar.controller;

import by.chebotar.controller.command.Command;
import by.chebotar.controller.command.CommandProvider;
import by.chebotar.controller.command.CommandType;
import by.chebotar.controller.command.Router;
import by.chebotar.controller.exception.ControllerException;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;
import by.chebotar.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns={"/index"}, name="index")
public class FrontController extends HttpServlet {

    private static Logger LOGGER = LogManager.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().takeCommand(CommandType
                .of(request.getParameter("command")).orElse(CommandType.SHOW_EMPTY_PAGE));
        ResponseContent responseContent = command.execute(request, response);
        if (responseContent.getRouter().getType().equals(Router.Type.REDIRECT)) {
            response.sendRedirect(responseContent.getRouter().getRoute());
        } else {
            request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
        }
    }
}
