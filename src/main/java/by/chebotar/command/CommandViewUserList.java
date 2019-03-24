package by.chebotar.command;

import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CommandViewUserList implements  Command{
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            List<User> userList = userService.getAll();
            session.setAttribute("userList", userList);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
        } catch (DaoException e) {
            session.setAttribute("exception", true);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
        }
    }
}
