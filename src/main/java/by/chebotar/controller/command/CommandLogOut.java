package by.chebotar.controller.command;

import by.chebotar.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLogOut implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("../WEB-INF/jsp/login.jsp",Router.Type.FORWARD));
        return responseContent;
    }
}
