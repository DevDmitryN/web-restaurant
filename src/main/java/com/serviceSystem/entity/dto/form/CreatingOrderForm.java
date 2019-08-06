package com.serviceSystem.entity.dto.form;

import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.entity.dto.DishInOrderDto;
import com.serviceSystem.entity.dto.TableDto;

import java.util.List;

public class CreatingOrderForm {
    private List<DishInOrderDto> dishes;
    private TableDto table;
    private String bookingTimeBegin;
    private String bookingTimeEnd;


    public List<DishInOrderDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishInOrderDto> dishes) {
        this.dishes = dishes;
    }

    public TableDto getTable() {
        return table;
    }

    public void setTable(TableDto table) {
        this.table = table;
    }

    public String getBookingTimeBegin() {
        return bookingTimeBegin;
    }

    public void setBookingTimeBegin(String bookingTimeBegin) {
        this.bookingTimeBegin = bookingTimeBegin;
    }

    public String getBookingTimeEnd() {
        return bookingTimeEnd;
    }

    public void setBookingTimeEnd(String bookingTimeEnd) {
        this.bookingTimeEnd = bookingTimeEnd;
    }
}
