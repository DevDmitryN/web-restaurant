package com.serviceSystem.entity;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class User<ID extends Number> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;



    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, phoneNumber);
    }
}
