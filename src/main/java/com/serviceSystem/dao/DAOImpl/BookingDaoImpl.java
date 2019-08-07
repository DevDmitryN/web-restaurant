package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.BookingDao;
import com.serviceSystem.entity.Booking;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class BookingDaoImpl extends BaseDAOImpl<Booking,Long> implements BookingDao {

    public BookingDaoImpl() {
        super(Booking.class);
    }

    @Override
    @Transactional
    public boolean isBookingPeriodValidForTable(LocalDateTime begin, LocalDateTime end, int tableId) {
        String hql = "from Booking b where :begin between b.bookingBegin and b.bookingEnd" +
                " or :end between b.bookingBegin and b.bookingEnd and b.table.id = :tableId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("begin",begin);
        query.setParameter("end",end);
        query.setParameter("tableId",tableId);
        return query.list().size() == 0;
    }
}
