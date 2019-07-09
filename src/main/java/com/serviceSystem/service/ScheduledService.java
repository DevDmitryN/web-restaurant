package com.serviceSystem.service;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;


public class ScheduledService implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);
    @Override
    public void run() {
        OrderService orderService = OrderService.getInstance();
        List<Order> orders = orderService.getWithFreeTable();
        LocalDateTime now = LocalDateTime.now();
        for (Order order : orders) {
            LocalDateTime bookingTime = order.getBookingTime().minus(Duration.ofHours(1));
            Duration duration = Duration.between(bookingTime,now);
            if(duration.toMinutes() <= 60 && duration.toMinutes() > 0){
                order.getTable().setFreeStatus(false);
                order.setStatus(Status.BEING_PERFORMED);
                TableService.getInstance().update(order.getTable());
                order.setStatus(Status.BEING_PERFORMED);
                logger.info("Table " + order.getTable().getId() + " was updated");
                logger.info("Order status " + order.getId() + " was changed to " + Status.BEING_PERFORMED);
            }
        }
    }
}
