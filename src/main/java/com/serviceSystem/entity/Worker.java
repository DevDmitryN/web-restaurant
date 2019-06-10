package com.serviceSystem.entity;

import com.serviceSystem.entity.enums.Roles;

import java.util.List;

public class Worker {
    private Roles role;
    private List<Order> orders;

    public Worker(){

    }

    public Worker(Roles role, List<Order> orders) {
        this.role = role;
        this.orders = orders;
    }

    public Worker(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
