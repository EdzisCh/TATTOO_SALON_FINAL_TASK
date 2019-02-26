package by.chebotar.controller.command;

import by.chebotar.controller.exception.CommandException;
import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;
import by.chebotar.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLogIn implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService;
        try {
             userService = new UserServiceImpl();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        User user;
        try {
            user = userService.getByLogin(login);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        if (user != null && user.getPassword().equals(pass)){
            request.getSession().setAttribute("user", login);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request,response);
        } else {
            return CommandProvider.getInstance().takeCommand(CommandType.LOG_IN).execute(request,response);
        }
    }
}
