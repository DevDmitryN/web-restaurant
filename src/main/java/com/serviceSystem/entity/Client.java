package com.serviceSystem.entity;

public class Client extends User {
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
