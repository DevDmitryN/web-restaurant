package com.serviceSystem.entity.dto;

import com.serviceSystem.service.validation.UniqueEmail;
import com.serviceSystem.service.validation.UniquePhoneNumber;

import javax.validation.constraints.*;


@UniquePhoneNumber
@UniqueEmail
public abstract class UserDto {
    private long id;

    @Size(min = 1,max = 50,message = "Name must be between 1 and 50 characters")
    private String name;

    @Size(min = 1,max = 50,message = "Name must be between 1 and 50 characters")
    private String surname;

    @Pattern(regexp = "\\A([\\w-]+)@([\\w-]+)[.](\\w{1,5})\\z",message = "Incorrect email")
    @Size(min = 1,max = 50,message = "Name must be between 1 and 50 characters")
    private String email;

    @Size(min = 1,max = 50,message = "Name must be between 1 and 50 characters")
    @Pattern(regexp = "\\A(44|29|25|17)(\\s|-)?\\d{3}(\\s|-)?\\d{2}(\\s|-)?\\d{2}\\z",message = "Incorrect phone number")
    private String phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
