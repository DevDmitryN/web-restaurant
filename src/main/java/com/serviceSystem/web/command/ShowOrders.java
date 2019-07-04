package com.serviceSystem.web.command;

import com.serviceSystem.DTO.OrderDTO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrders extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURL() + " " + req.getContextPath() + " " + req.getQueryString());
        ModelMapper modelMapper = new ModelMapper();
        OrderService orderService = OrderService.getInstance();
        TableService tableService = TableService.getInstance();
        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        String tableId = req.getParameter("tableId");
        if(tableId == null || tableId.equals("all")){
            for (Order order : orderService.getAll()) {
                orders.add(modelMapper.map(order,OrderDTO.class));
            }
        }else{
            for (Order order : orderService.getOrdersByTableId(Integer.parseInt(tableId))) {
                orders.add(modelMapper.map(order,OrderDTO.class));
            }
        }
        List<RestaurantTable> tables = tableService.getAll();
        req.setAttribute("orders",orders);
        req.setAttribute("tables",tables);
        req.getRequestDispatcher("showOrders.jsp").forward(req,resp);
    }
}
