package com.serviceSystem.web.command;

import com.serviceSystem.service.OrderService;
import com.serviceSystem.web.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrder extends Command {
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = OrderService.getInstance();
        long id = Long.valueOf(req.getParameter("id"));
        orderService.delete(id);
        resp.sendRedirect("/frontController?command=show_orders");
//        return Action.SHOW_ORDERS.getCommand();
        return null;
    }
}
