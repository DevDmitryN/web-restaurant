package com.serviceSystem.servlet;

import com.serviceSystem.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = OrderService.getInstance();
        long id = Long.valueOf(req.getParameter("id"));
        orderService.delete(id);
        resp.sendRedirect("showOrders");
    }
}
