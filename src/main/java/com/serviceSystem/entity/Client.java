package com.serviceSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clients", schema = "restaurantdb")
public class Client extends User {
    @Column(name = "card_number")
    private String cardNumber;

    public Client(){

    }

    public Client(String name, String surname, String password, String email, String phoneNumber, String cardNumber) {
        super(name, surname, password, email, phoneNumber);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
