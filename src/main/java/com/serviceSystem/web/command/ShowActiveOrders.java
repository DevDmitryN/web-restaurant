package com.serviceSystem.web.command;

import com.serviceSystem.DTO.OrderDTO;
import com.serviceSystem.entity.Client;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.web.filter.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowActiveOrders extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = SessionHandler.getClient(req.getSession());
        OrderService orderService = OrderService.getInstance();
        List<OrderDTO> orders =  OrderDTO.toListOfOrderDTO(orderService.getNotCompletedByClientId(client.getId()));
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("activeOrders.jsp").forward(req,resp);
    }
}
