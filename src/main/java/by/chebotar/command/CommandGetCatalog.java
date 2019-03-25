package by.chebotar.command;

import by.chebotar.domain.Tattoo;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.TattooService;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CommandGetCatalog implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            TattooService tattooService = ServiceFactory.getInstance().getTattooService();
            List<Tattoo> tattooList = tattooService.getAllTattoo();
            session.setAttribute("viewCatalog", true);
            session.setAttribute("tattooList", tattooList);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
        } catch (ServiceException e) {
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
        }
    }
}
