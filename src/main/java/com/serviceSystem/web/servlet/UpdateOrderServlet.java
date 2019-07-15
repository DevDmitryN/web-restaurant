package com.serviceSystem.web.servlet;

import com.serviceSystem.entity.DTO.OrderDTO;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;
import com.serviceSystem.service.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateOrderServlet extends HttpServlet {
    @Autowired
    OrderService orderService;
    @Autowired
    TableService tableService;
    @Autowired
    DishService dishService;
    @Autowired
    WorkerService workerService;
    Order order;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.valueOf(req.getParameter("id"));

        List<RestaurantTable> tables = tableService.getAll();
        List<Dish> dishes = dishService.getAll();
        List<Worker> workers = workerService.getAll();

        order = orderService.getById(orderId);

        ModelMapper modelMapper = new ModelMapper();
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        req.setAttribute("order", orderDTO);
        req.setAttribute("tables", tables);
        req.setAttribute("dishes", dishes);
        req.setAttribute("workers", workers);
        resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");

        req.getRequestDispatcher("updateOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = tableService.getAll();
        List<Worker> workers = workerService.getAll();
        String strId = req.getParameter("tableId");
        int tableId = Integer.valueOf(strId);
        long workerId = Long.valueOf(req.getParameter("workerId"));
        String bookingTime = req.getParameter("bookingTime");
        for (RestaurantTable t : tables) {
            if (tableId == t.getId()) {
                order.setTable(t);
            }
        }
        for (Worker w : workers) {
            if (workerId == w.getId()) {
                order.setWorker(w);
            }
        }
        Status status = Status.valueOf(req.getParameter("status"));
        order.setStatus(status);
        orderService.update(order);
        resp.sendRedirect("frontController?command=show_orders");
    }
}
