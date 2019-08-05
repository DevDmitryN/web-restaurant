package com.serviceSystem.entity.dto;

import com.serviceSystem.service.validation.UniqueEmail;

public class WorkerDto extends UserDto{

    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
