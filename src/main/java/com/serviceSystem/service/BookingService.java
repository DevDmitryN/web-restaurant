package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.BookingDao;
import com.serviceSystem.entity.Booking;
import com.serviceSystem.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {
    @Autowired
    private BookingDao bookingDao;

    public void save(Booking booking){

    }
    public void save(Order order){
        Booking booking = new Booking(order.getTable(),order.getBookingTimeBegin(),order.getBookingTimeEnd());
        bookingDao.save(booking);
    }

    public boolean isBookingPeriodValidForTable(LocalDateTime begin,LocalDateTime end, int tableId){
        return bookingDao.isBookingPeriodValidForTable(begin,end,tableId);
    }
}
