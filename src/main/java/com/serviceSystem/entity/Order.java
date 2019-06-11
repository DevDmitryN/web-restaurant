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

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;
    @ManyToMany
    @JoinTable(
            name = "order_dish",
            schema = "restaurantdb",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
    private List<Dish> dishes;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    public Order(){

    }
    public Order(RestaurantTable table, List<Dish> dishes,Client client,Worker worker){
        init(null,table,dishes,client,worker);
    }
    public Order(RestaurantTable table, List<Dish> dishes) {
        init(null,table,dishes,null,null);
    }
    public Order(Status status, RestaurantTable table, List<Dish> dishes) {
        init(status,table,dishes,null,null);
    }
    public void init(Status status, RestaurantTable table,List<Dish> dishes,Client client,Worker worker){
        this.status = status != null ? status : Status.NOT_TAKEN;;
        this.table = table;
        this.dishes = dishes;
        dishes.forEach( a -> totalPrice = totalPrice.add(a.getPrice()) );
        this.client = client;
        this.worker = worker;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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
