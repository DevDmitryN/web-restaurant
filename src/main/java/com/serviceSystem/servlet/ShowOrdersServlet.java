package com.serviceSystem.servlet;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrdersServlet extends HttpServlet {
    OrderService orderService = OrderService.getInstance();
    TableService tableService = TableService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders;
        String tableId = req.getParameter("tableId");
        if(tableId == null || tableId.equals("all")){
           orders  = orderService.getAll();
        }else{
            orders = orderService.getOrdersByTableId(Integer.parseInt(tableId));
        }
//        orders.forEach( o -> System.out.println(o));
        List<RestaurantTable> tables = tableService.getAll();
        req.setAttribute("orders",orders);
        req.setAttribute("tables",tables);
        req.getRequestDispatcher("showOrders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String tableId = req.getParameter("tableId");
        req.setAttribute("tableId",req.getParameter("tableId"));
        doGet(req,resp);
    }
}
