package com.serviceSystem.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking",schema = "restaurantdb")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;
    @Column(name = "bookingBegin")
    private LocalDateTime bookingBegin;
    @Column(name = "bookingEnd")
    private LocalDateTime bookingEnd;

    public Booking(){}

    public Booking(RestaurantTable table, LocalDateTime bookingBegin, LocalDateTime bookingEnd) {
        this.table = table;
        this.bookingBegin = bookingBegin;
        this.bookingEnd = bookingEnd;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public LocalDateTime getBookingBegin() {
        return bookingBegin;
    }

    public void setBookingBegin(LocalDateTime bookingBegin) {
        this.bookingBegin = bookingBegin;
    }

    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }

    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }
}
