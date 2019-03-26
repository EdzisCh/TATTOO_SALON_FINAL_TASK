package by.chebotar.command;

import by.chebotar.domain.TattooOrder;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.OrderService;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class CommandMakeAnOrde implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TattooOrder tattooOrder = new TattooOrder();
        tattooOrder.setPrice(Float.parseFloat(request.getParameter("price")));
        //tattooOrder.setDate(Date(request.getParameter("date")));
        tattooOrder.setIdTattoo(Integer.parseInt(request.getParameter("tattooId")));
        tattooOrder.setIdUser(Integer.parseInt(request.getParameter("userId")));
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        try {
            orderService.createOrder(tattooOrder);
        } catch (ServiceException e) {
            session.setAttribute("exception", "Sorry, we cant do it now");
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
        }
        session.setAttribute("success", true);
        return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
    }
}
