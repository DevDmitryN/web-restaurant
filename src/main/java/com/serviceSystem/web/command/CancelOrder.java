package com.serviceSystem.web.command;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelOrder extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        Order order = OrderService.getInstance().getById(id);
        order.setStatus(Status.CANCELLED);
        OrderService.getInstance().update(order);
        resp.sendRedirect("frontController?command=show_active_orders");
    }
}
