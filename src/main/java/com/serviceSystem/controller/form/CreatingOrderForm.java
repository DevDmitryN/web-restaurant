package com.serviceSystem.controller.form;

import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.entity.dto.TableDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CreatingOrderForm {
    private List<DishDto> dishes;
    private List<TableDto> tables;
    private int tableId;
    private int hour;
    private int minutes;
//    private int month;
//    private int day;
//    private int year;
    private LocalDateTime bookingTime;
    public CreatingOrderForm(){}

    public CreatingOrderForm(List<DishDto> dishes, List<TableDto> tables) {
        this.dishes = dishes;
        this.tables = tables;
    }

    public List<DishDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDto> dishes) {
        this.dishes = dishes;
    }

    public List<TableDto> getTables() {
        return tables;
    }

    public void setTables(List<TableDto> tables) {
        this.tables = tables;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

//    public int getMonth() {
//        return month;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public LocalDateTime getBookingTime(){
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
//        year = bookingTime.getYear();
//        month = bookingTime.getMonth().getValue();
//        day = bookingTime.getDayOfMonth();
        hour = bookingTime.getHour();
        minutes = bookingTime.getMinute();

    }
    public LocalDateTime getBookingTimeFromFields(){
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(hour,minutes));
    }
}
