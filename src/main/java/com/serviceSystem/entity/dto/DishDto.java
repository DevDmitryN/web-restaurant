package com.serviceSystem.entity.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class DishDto {
    private int id;

    @NotNull
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    private String description;

    @Positive(message = "Can't be negative or zero")
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDto dishDto = (DishDto) o;
        return id == dishDto.id &&
                Float.compare(dishDto.price, price) == 0 &&
                Objects.equals(name, dishDto.name) &&
                Objects.equals(description, dishDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }
}
