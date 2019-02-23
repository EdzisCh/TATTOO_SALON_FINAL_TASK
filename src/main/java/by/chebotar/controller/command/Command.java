package by.chebotar.controller.command;

import by.chebotar.controller.exception.CommandException;
import by.chebotar.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * Command
 */
@FunctionalInterface
public interface Command extends BiFunction<HttpServletRequest, HttpServletResponse, ResponseContent> {

    /**
     * Execute command
     * @param request is used for extracting request parameters
     * @return response content
     */
    ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    @Override
    default ResponseContent apply(HttpServletRequest request, HttpServletResponse response){
        try{
            return execute(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException("Cannot execute command" + this.getClass().getName(),e);
        }
    }
}
