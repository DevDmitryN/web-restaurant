package com.serviceSystem.web.command;

import com.serviceSystem.entity.DTO.OrderDTO;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Order;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.web.filter.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowActiveOrders extends Command {
    @Autowired
    OrderService orderService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = SessionHandler.getClient(req.getSession());
        List<Order> orders =  orderService.getNotCompletedByClientId(client.getId());
        List<OrderDTO> dtoOrders = null;
        if(orders != null){
            dtoOrders =  OrderDTO.toListOfOrderDTO(orders);
        }
        req.setAttribute("orders", dtoOrders);
        req.getRequestDispatcher("activeOrders.jsp").forward(req,resp);
    }
}
