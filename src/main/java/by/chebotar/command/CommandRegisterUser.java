package by.chebotar.command;

import by.chebotar.controller.exception.CommandException;
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

public class CommandRegisterUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        RoleService roleService = ServiceFactory.getInstance().getRoleService();
        User newUser;
        Role role;
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String secondName = request.getParameter("secondName");

            newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setFirstName(firstName);
            newUser.setLastName(secondName);

            int id = userService.registerAndGetId(newUser);
            role = Role.CLIENT;
            role.setIdUser(id);
            roleService.setRole(role);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = request.getSession();
        session.setAttribute("userLogin", newUser.getLogin());

        return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
    }
}
