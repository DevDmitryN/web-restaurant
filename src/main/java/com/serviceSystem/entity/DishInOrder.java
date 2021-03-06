package com.serviceSystem.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


@Component
@Entity
@Table(name = "order_dish",schema = "restaurantdb")
public class DishInOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column
    private int amount;

    public DishInOrder() {

    }

    public DishInOrder(Order order, Dish dish, int amount) {
        this.order = order;
        this.dish = dish;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
