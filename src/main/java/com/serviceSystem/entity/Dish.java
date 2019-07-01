package com.serviceSystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="dishes",schema = "restaurantdb")
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private float price;
    @Column
    private String description;
    @ManyToMany(mappedBy = "dishes")
    List<Order> orders = new ArrayList<Order>();

    public Dish(){}


    public Dish(String name, float price) {
        init(name,price,null);
    }

    public Dish(String name, float price, String description) {
        init(name,price,description);
    }
    private void init(String name, float price, String description){
        this.name = name.trim();
        this.price = price;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", price=" + price +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
