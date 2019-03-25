package by.chebotar.command;

import by.chebotar.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandMakeAnOreder implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        return null;
    }
}
