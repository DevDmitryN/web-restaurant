package com.serviceSystem.web.servlet;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MakeOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DishService dishService = DishService.getInstance();
        TableService tableService = TableService.getInstance();
        List<RestaurantTable> tables = tableService.getAll();
        List<Dish> dishes = dishService.getAll();
        req.setAttribute("tables",tables);
        req.setAttribute("dishes",dishes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/makeOrder.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = TableService.getInstance().getAll();
        List<Dish> dishes = DishService.getInstance().getAll();
        int tableId = Integer.valueOf(req.getParameter("table"));
        List<Dish> orderedDishes = new ArrayList<Dish>();
        for(Dish dish : dishes){
            String parameter = "dish_"+dish.getId();
            int amount = Integer.parseInt(req.getParameter(parameter));
            for(int i=0; i < amount ; i++){
                orderedDishes.add(dish);
            }
        }
        RestaurantTable orderedTable = null;
        for (RestaurantTable table : tables) {
            if(table.getId() == tableId){
                orderedTable = table;
            }
        }
        String bookingTime = req.getParameter("bookingTime");
        Client client = new Client();
        client.setId(1);
        LocalDateTime bookingDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(bookingTime));
        Order order = new Order(orderedTable,orderedDishes);
        order.setClient(client);
        order.setBookingTime(bookingDateTime);
        OrderService.getInstance().save(order);
        resp.sendRedirect("/view/success.jsp");
    }
}