package com.serviceSystem.web.command;

import com.serviceSystem.DTO.OrderDTO;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Order;
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
        List<Order> orders =  orderService.getNotCompletedByClientId(client.getId());
        List<OrderDTO> dtoOrders = null;
        if(orders != null){
            dtoOrders =  OrderDTO.toListOfOrderDTO(orders);
        }
        req.setAttribute("orders", dtoOrders);
        req.getRequestDispatcher("activeOrders.jsp").forward(req,resp);
    }
}
