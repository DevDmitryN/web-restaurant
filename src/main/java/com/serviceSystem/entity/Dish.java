package com.serviceSystem.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Component
@Entity
@Table(name="dishes",schema = "restaurantdb")
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private float price;
    @Column
    private String description;

    @Column
    private boolean isInMenu = true;

    @OneToMany(mappedBy = "dish")
    private List<DishInOrder> orders;

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

    public Integer getId() {
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


    public List<DishInOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<DishInOrder> orders) {
        this.orders = orders;
    }

    public boolean isInMenu() {
        return isInMenu;
    }

    public void setInMenu(boolean inMenu) {
        isInMenu = inMenu;
    }


}
