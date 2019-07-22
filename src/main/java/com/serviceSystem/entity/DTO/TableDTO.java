package com.serviceSystem.entity.DTO;

public class TableDTO {
    private int id;
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


    @Override
    public String toString() {
        return  "Номер: " + id +
                ", вместимость: " + capacity +
                (freeStatus ? " свободен" : " занят");
    }
}
