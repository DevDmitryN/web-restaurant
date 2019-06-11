package com.serviceSystem.entity;

import com.serviceSystem.entity.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders",schema = "restaurantdb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private BigDecimal totalPrice = new BigDecimal(0);
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private RestaurantTable table;
    @ManyToMany
    @JoinTable(
            name = "order_dish",
            schema = "restaurantdb",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
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
