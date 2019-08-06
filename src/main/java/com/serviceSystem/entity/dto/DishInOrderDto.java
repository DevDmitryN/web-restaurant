package com.serviceSystem.entity.dto;

public class DishInOrderDto {

    private DishDto dish;
    private int amount;


    public DishDto getDish() {
        return dish;
    }

    public void setDish(DishDto dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
