package com.serviceSystem.web.servlet;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;
import com.serviceSystem.service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class UpdateOrderServlet extends HttpServlet {
    Order order;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.valueOf(req.getParameter("id"));
        List<RestaurantTable> tables = TableService.getInstance().getAll();
        List<Dish> dishes = DishService.getInstance().getAll();
        List<Worker> workers = WorkerService.getInstance().getAll();
        order = OrderService.getInstance().getOrderById(orderId);

        req.setAttribute("order",order);
        req.setAttribute("tables",tables);
        req.setAttribute("dishes",dishes);
        req.setAttribute("workers",workers);
        resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        req.getRequestDispatcher("view/updateOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = TableService.getInstance().getAll();
        List<Worker> workers = WorkerService.getInstance().getAll();
        int tableId = Integer.valueOf(req.getParameter("tableId"));
        long workerId = Long.valueOf(req.getParameter("workerId"));
        String bookingTime = req.getParameter("bookingTime");
        for (RestaurantTable t : tables) {
            if(tableId == t.getId()){
                order.setTable(t);
            }
        }
        for (Worker w : workers) {
            if(workerId == w.getId()){
                order.setWorker(w);
            }
        }
        if(!bookingTime.equals("")){
            LocalDateTime newBookingTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(bookingTime));
            order.setBookingTime(newBookingTime);
        }
        OrderService.getInstance().update(order);
        resp.sendRedirect("/frontController?command=show_orders");
    }
}