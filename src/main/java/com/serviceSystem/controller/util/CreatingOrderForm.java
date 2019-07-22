package com.serviceSystem.controller.util;

import com.serviceSystem.entity.DTO.DishDTO;
import com.serviceSystem.entity.DTO.TableDTO;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.RestaurantTable;

import java.time.LocalDateTime;
import java.util.List;

public class CreatingOrderForm {
    private List<DishDTO> dishes;
    private List<TableDTO> tables;
    private int tableId;
    private int hour;
    private int minutes;
    private int month;
    private int day;
    private int year;
    private LocalDateTime bookingTime;
    public CreatingOrderForm(){}

    public CreatingOrderForm(List<DishDTO> dishes, List<TableDTO> tables) {
        this.dishes = dishes;
        this.tables = tables;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public List<TableDTO> getTables() {
        return tables;
    }

    public void setTables(List<TableDTO> tables) {
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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
        year = bookingTime.getYear();
        month = bookingTime.getMonth().getValue();
        day = bookingTime.getDayOfMonth();
        hour = bookingTime.getHour();
        minutes = bookingTime.getMinute();

    }
    public LocalDateTime getBookingTimeFromFields(){
        return LocalDateTime.of(year,month,day,hour,minutes);
    }
}
