package com.serviceSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviceSystem.entity.enums.Role;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name="workers",schema = "restaurantdb")
public class Worker extends User<Integer>{
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_in_staff")
    private boolean isInStaff = true;

    @OneToMany(mappedBy = "worker")
    @JsonIgnore
    private List<Order> orders;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isInStaff() {
        return isInStaff;
    }

    public void setInStaff(boolean inStaff) {
        isInStaff = inStaff;
    }
}
