package com.serviceSystem.dao.DAOInterface;

import com.serviceSystem.entity.Booking;

import java.time.LocalDateTime;

public interface BookingDao extends BaseDAO<Booking,Long> {

    boolean isBookingPeriodValidForTable(LocalDateTime begin,LocalDateTime end,int tableId);
}
