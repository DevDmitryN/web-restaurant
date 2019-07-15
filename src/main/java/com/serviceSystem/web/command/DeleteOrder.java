package com.serviceSystem.web.command;

import com.serviceSystem.service.OrderService;
import com.serviceSystem.web.Action;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrder extends Command {
    @Autowired
    OrderService orderService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        orderService.delete(id);
        resp.sendRedirect("frontController?command=show_orders");
    }
}
