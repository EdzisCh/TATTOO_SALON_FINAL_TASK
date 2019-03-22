package by.chebotar.controller;

import by.chebotar.command.Command;
import by.chebotar.command.CommandProvider;
import by.chebotar.command.CommandType;
import by.chebotar.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(/* Provide your code here**/)
public class AjaxController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE);
        ResponseContent responseContent = command.execute(request,response);

        // Provide your code here

    }
}
