package by.chebotar.controller.command;

import by.chebotar.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface TattooCommand extends Command {

    @Override
    ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
