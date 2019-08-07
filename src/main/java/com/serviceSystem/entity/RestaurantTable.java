package com.serviceSystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Component
@Entity
@Table(name="tables",schema = "restaurantdb")
public class RestaurantTable implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int capacity;
    @Column(name = "is_free")
    private boolean freeStatus = true;
    @OneToMany(mappedBy = "table")
    @JsonIgnore
    private List<Order> order;

    public RestaurantTable(){}

    public RestaurantTable(int capacity) {
        this.capacity = capacity;
    }

    public RestaurantTable(int capacity, boolean freeStatus) {
        this.capacity = capacity;
        this.freeStatus = freeStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean getFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(boolean freeStatus) {
        this.freeStatus = freeStatus;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getFreeStatusAsString(){
        return freeStatus ? "Не занят" : "Занят";
    }
}
