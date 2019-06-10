package com.serviceSystem.servlet;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.ServiceSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderServlet extends HttpServlet {
    ServiceSystem serviceSystem = new ServiceSystem();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = serviceSystem.getTables();
        List<Dish> dishes = serviceSystem.getDishes();
        req.setAttribute("tables",tables);
        req.setAttribute("dishes",dishes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("sendOrder.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = serviceSystem.getTables();
        List<Dish> dishes = serviceSystem.getDishes();
        int tableId = Integer.valueOf(req.getParameter("table"));
        List<Dish> orderedDishes = new ArrayList<Dish>();
        for(Dish d : dishes){
            String id = req.getParameter(Integer.toString(d.getId()));
            if(id == null){
                continue;
            }
            System.out.println(d.getId() + ";"+ id);
            if (d.getId() == Integer.valueOf(id) ) {
                orderedDishes.add(d);
            }
        }
        RestaurantTable orderedTable = null;
        for (RestaurantTable table : tables) {
            if(table.getId() == tableId){
                orderedTable = table;
            }
        }
        Order order = new Order(orderedTable,orderedDishes);
        serviceSystem.takeOrder(order);
    }
}
