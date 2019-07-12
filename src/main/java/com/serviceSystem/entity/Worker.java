package com.serviceSystem.entity;

import com.serviceSystem.entity.enums.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="workers",schema = "restaurantdb")
public class Worker extends User<Integer>{
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "worker")
    private List<Order> orders;

    public Worker(){

    }
//    public Worker(String name, String surname, String password, String email, String phoneNumber, Role role){
//        super(name, surname, password, email, phoneNumber);
//        this.role = role;
//    }



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void setRole(String role){
        this.role = Role.valueOf(role);
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
