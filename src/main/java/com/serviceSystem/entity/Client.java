package com.serviceSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients", schema = "restaurantdb")
public class Client extends User<Long> {
    @Column(name = "card_number")
    private String cardNumber;
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public Client(){

    }

//    public Client(String name, String surname, String password, String email, String phoneNumber, String cardNumber) {
//        super(name, surname, password, email, phoneNumber);
//        this.cardNumber = cardNumber;
//    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
