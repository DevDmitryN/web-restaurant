package com.serviceSystem.servlet;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.ServiceSystem;
import com.serviceSystem.service.TableService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServlet extends HttpServlet {
//    private ServiceSystem serviceSystem = ServiceSystem.getInstance();
    private DishService dishService = DishService.getInstance();
    private TableService tableService = TableService.getInstance();
    private OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = tableService.getAll();
        List<Dish> dishes = dishService.getAll();
        req.setAttribute("tables",tables);
        req.setAttribute("dishes",dishes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("sendOrder.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = tableService.getAll();
        List<Dish> dishes = dishService.getAll();
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
        orderedDishes.forEach( d -> System.out.println(d));
        System.out.println(orderedTable);
        System.out.println(bookingTime);

        Client client = new Client();
        client.setId(1);
        LocalDateTime bookingDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(bookingTime));
        Order order = new Order(orderedTable,orderedDishes);
        order.setClient(client);
        order.setBookingTime(bookingDateTime);
        orderService.save(order);
        resp.sendRedirect("/");
    }
}
