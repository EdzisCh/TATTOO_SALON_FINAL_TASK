package by.chebotar.command;

import by.chebotar.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGetLoginPage implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return CommandProvider.getInstance().takeCommand(CommandType.LOG_IN).execute(request,response);
    }
}
