package by.chebotar.command;

import by.chebotar.domain.Tattoo;
import by.chebotar.domain.TattooOrder;
import by.chebotar.domain.User;
import by.chebotar.dto.ResponseContent;
import by.chebotar.service.OrderService;
import by.chebotar.service.ServiceFactory;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class CommandMakeAnOrder implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = new User();
        user.setEmail(request.getParameter("email-order"));
        user.setPassword(request.getParameter("password-order"));

        try {
            user = userService.signUp(user);
        } catch (ServiceException e) {
            session.setAttribute("exception", "Incorrect password or email");
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request, response);
        }

        Tattoo tattoo = (Tattoo) session.getAttribute("tattoo");
        TattooOrder tattooOrder = new TattooOrder();
        tattooOrder.setPrice(tattoo.getPrice());
        //tattooOrder.setDate(tattoo.getDateOfCreation());
        tattooOrder.setIdTattoo(tattoo.getId());
        tattooOrder.setIdUser(user.getId());

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
