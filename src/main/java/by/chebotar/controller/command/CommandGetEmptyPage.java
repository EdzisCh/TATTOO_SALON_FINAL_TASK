package by.chebotar.controller.command;

import by.chebotar.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandGetEmptyPage implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response){
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/WEB-INF/jsp/main.jsp",Router.Type.FORWARD));
        return responseContent;
    }
}
