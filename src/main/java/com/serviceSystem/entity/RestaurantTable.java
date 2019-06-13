package com.serviceSystem.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    public boolean isFreeStatus() {
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

    @Override
    public String toString() {
        return "RestaurantTable{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", freeStatus=" + freeStatus +
                '}';
    }
}
