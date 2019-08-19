package com.serviceSystem.entity.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TableDto {
    private int id;
    @NotNull
    @Positive(message = "Capacity can't be negative or zero")
    private int capacity;
    private boolean freeStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(boolean freeStatus) {
        this.freeStatus = freeStatus;
    }

}
