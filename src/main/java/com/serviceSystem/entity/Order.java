package com.serviceSystem.entity;

import com.serviceSystem.entity.enums.Status;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private long id;
    private BigDecimal totalPrice = new BigDecimal(0);
    private Status status;
    private RestaurantTable table;
    private List<Dish> dishes;
//    private User client;
//    private User worker;

    public Order(){

    }
    public Order(RestaurantTable table, List<Dish> dishes) {
        init(null,table,dishes);
    }
    public Order(Status status, RestaurantTable table, List<Dish> dishes) {
        init(status,table,dishes);
    }
    public void init(Status status, RestaurantTable table,List<Dish> dishes){
        this.status = status != null ? status : Status.NOT_TAKEN;;
        this.table = table;
        this.dishes = dishes;
        dishes.forEach( a -> totalPrice = totalPrice.add(a.getPrice()) );
    }
    public long getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = new BigDecimal(totalPrice);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", table=" + table +
                ", dishes=" + dishes +
                '}';
    }
}
