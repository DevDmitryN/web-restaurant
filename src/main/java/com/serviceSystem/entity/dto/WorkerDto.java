package com.serviceSystem.entity.dto;

import com.serviceSystem.service.validation.UniqueEmail;

public class WorkerDto extends UserDto{

    private String role;
    private boolean isInStaff = true;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isInStaff() {
        return isInStaff;
    }

    public void setInStaff(boolean inStaff) {
        isInStaff = inStaff;
    }
}
