package com.serviceSystem.servlet;

import com.serviceSystem.entity.Order;
import com.serviceSystem.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowDishesOfOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.valueOf(req.getParameter("id"));
        Order order = OrderService.getInstance().getOrderById(orderId);
        req.setAttribute("orderId",order.getId());
        req.setAttribute("dishes",order.getDishes());
        resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        req.getRequestDispatcher("veiw/showDishesOfOrder.jsp").forward(req,resp);
    }
}
