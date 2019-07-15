package com.serviceSystem.service;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduledService implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private TableService tableService;

    @Override
    public void run() {
        List<Order> orders = orderService.getNotTakenWithFreeTable();
        LocalDateTime now = LocalDateTime.now();
        for (Order order : orders) {
            LocalDateTime bookingTime = order.getBookingTime().minus(Duration.ofHours(1));
            Duration duration = Duration.between(bookingTime,now);
            if(duration.toMinutes() <= 60 && duration.toMinutes() > 0){
                order.getTable().setFreeStatus(false);
                order.setStatus(Status.BEING_PERFORMED);
                tableService.update(order.getTable());
                order.setStatus(Status.BEING_PERFORMED);
                logger.info("Table " + order.getTable().getId() + " was updated");
                logger.info("Order status " + order.getId() + " was changed to " + Status.BEING_PERFORMED);
            }
        }
    }
}
