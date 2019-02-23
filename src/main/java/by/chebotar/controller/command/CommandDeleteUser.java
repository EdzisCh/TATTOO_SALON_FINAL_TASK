package by.chebotar.controller.command;

import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
        } catch (ServiceException e) {
            //
        }
        int id = Integer.parseInt(request.getParameter("userId"));
        /*User user = userService.getUserById(id);
        userService.deleteUser(user);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("?command=" + CommandType.SHOW_USER_LIST, Router.Type.REDIRECT));*/
        return null;//responseContent;
    }
}
