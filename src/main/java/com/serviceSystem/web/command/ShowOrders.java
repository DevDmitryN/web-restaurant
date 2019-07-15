package com.serviceSystem.web.command;

import com.serviceSystem.entity.DTO.OrderDTO;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrders extends Command {
    @Autowired
    OrderService orderService;
    @Autowired
    TableService tableService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        String tableId = req.getParameter("tableId");
        if(tableId == null || tableId.equals("all")){
            orders = OrderDTO.toListOfOrderDTO(orderService.getAll());
        }else{
            orders = OrderDTO.toListOfOrderDTO(orderService.getByTableId(Integer.parseInt(tableId)));
//            for (Order order : orderService.getByTableId(Integer.parseInt(tableId))) {
//                orders.add(modelMapper.map(order,OrderDTO.class));
//            }
        }
        List<RestaurantTable> tables = tableService.getAll();
        req.setAttribute("orders",orders);
        req.setAttribute("tables",tables);
        req.getRequestDispatcher("showOrders.jsp").forward(req,resp);
    }
}
