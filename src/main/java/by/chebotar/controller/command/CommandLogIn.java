package by.chebotar.controller.command;

import by.chebotar.domain.Role;
import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.RoleService;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogIn implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = ServiceFactory.getInstance().getUserService();
        RoleService roleService = ServiceFactory.getInstance().getRoleService();

        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        User user = new User();
        Role role;

        user.setEmail(email);
        user.setPassword(pass);

        try {
            user = userService.signUp(user);
            role = roleService.getRoleById(user.getId());
        } catch (ServiceException e) {
            return CommandProvider.getInstance().takeCommand(CommandType.LOG_IN).execute(request,response);
        }

        HttpSession session = request.getSession();

        if (user != null && role != Role.INCORRECT){
            session.setAttribute("userLogin", user.getLogin());

            if (role == Role.ADMIN){
                session.setAttribute("isAdmin", true);
                return CommandProvider.getInstance().takeCommand(CommandType.GET_ADMIN_PAGE).execute(request, response);
            } else {
                return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
            }
        } else {
            session.setAttribute("incorrectData", true);
            return CommandProvider.getInstance().takeCommand(CommandType.GET_LOG_IN_PAGE).execute(request,response);
        }
    }
}
