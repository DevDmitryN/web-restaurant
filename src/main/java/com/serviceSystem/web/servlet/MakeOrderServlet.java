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
        List<RestaurantTable> tables = tableService.getFree();
        List<Dish> dishes = dishService.getAll();
        req.setAttribute("tables", tables);
        req.setAttribute("dishes", dishes);
        req.setAttribute("year",LocalDate.now().getYear());
        req.getRequestDispatcher("makeOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService.getInstance().save(fillOrder(req));
        req.getRequestDispatcher("success.jsp").forward(req, resp);
    }

    private Order fillOrder(HttpServletRequest req) {
        List<RestaurantTable> tables = TableService.getInstance().getAll();
        List<Dish> dishes = DishService.getInstance().getAll();
        int tableId = Integer.valueOf(req.getParameter("table"));
        List<Dish> orderedDishes = new ArrayList<Dish>();
        for (Dish dish : dishes) {
            String parameter = "dish_" + dish.getId();
            int amount = Integer.parseInt(req.getParameter(parameter));
            if (amount != 0) {
                dish.setAmount(amount);
                orderedDishes.add(dish);
            }
        }
        RestaurantTable orderedTable = null;
        for (RestaurantTable table : tables) {
            if (table.getId() == tableId) {
                orderedTable = table;
            }
        }
        Client client = (Client) req.getSession().getAttribute("user");
        Order order = new Order();
        order.setTable(orderedTable);
        //order.setOrderDishComposites(orderedDishes);
        order.setClient(client);

        int hour = Integer.valueOf(req.getParameter("hour"));
        int minutes = Integer.valueOf(req.getParameter("minutes"));
        int month = Integer.valueOf(req.getParameter("month"));
        int day = Integer.valueOf(req.getParameter("day"));
        LocalDateTime bookingTime = LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(),month,day),LocalTime.of(hour,minutes));
        order.setBookingTime(bookingTime);
        return order;
    }
}
