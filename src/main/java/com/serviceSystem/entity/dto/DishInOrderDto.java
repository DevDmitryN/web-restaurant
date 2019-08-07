package com.serviceSystem.entity.dto;

import javax.validation.constraints.PositiveOrZero;

public class DishInOrderDto {

    private DishDto dish;
    @PositiveOrZero(message = "Amount of dish id order can't be negative")
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
