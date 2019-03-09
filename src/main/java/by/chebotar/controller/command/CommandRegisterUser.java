package by.chebotar.controller.command;

import by.chebotar.controller.exception.CommandException;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.dao.impl.JdbcDaoFactory;
import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO
public class CommandRegisterUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setFirst_name(firstName);
            newUser.setLast_name(lastName);
            userService.register(newUser);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return null;
    }
}
