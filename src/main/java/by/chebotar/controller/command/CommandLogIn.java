package by.chebotar.controller.command;

import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLogIn implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = ServiceFactory.getInstance().getUserService();

        String login = request.getParameter("email");
        String pass = request.getParameter("password");
        User user = new User();

        user.setEmail(login);

        try {
            user = userService.signUp(user);
        } catch (ServiceException e) {
            return CommandProvider.getInstance().takeCommand(CommandType.LOG_IN).execute(request,response);
        }

        if (user != null){
            if(user.getPassword().equals(pass)) {
                request.getSession(true).setAttribute(login, request.getSession().getId());
                Cookie cookie = new Cookie("user", user.getLogin());
                response.addCookie(cookie);

                return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
            } else {
                return CommandProvider.getInstance().takeCommand(CommandType.LOG_IN).execute(request,response);
            }
        } else {
            return CommandProvider.getInstance().takeCommand(CommandType.REGISTER_USER).execute(request,response);
        }
    }
}
