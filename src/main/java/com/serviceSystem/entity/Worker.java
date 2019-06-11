package com.serviceSystem.entity;

import com.serviceSystem.entity.enums.Roles;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="workers",schema = "restaurantdb")
public class Worker extends User{
    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;
    @OneToMany(mappedBy = "worker")
    private List<Order> orders;

    public Worker(){

    }
    public Worker(String name, String surname, String password, String email, String phoneNumber,Roles role){
        super(name, surname, password, email, phoneNumber);
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
