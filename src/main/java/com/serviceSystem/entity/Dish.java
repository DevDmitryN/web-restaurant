package com.serviceSystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="dishes",schema = "restaurantdb")
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String description;

    public Dish(){}


    public Dish(String name, double price) {
        init(name,price,null);
    }

    public Dish(String name, double price, String description) {
        init(name,price,description);
    }
    private void init(String name, double price, String description){
        this.name = name.trim();
        this.price = new BigDecimal(price);
        this.description = description.trim();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = new BigDecimal(price);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
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
