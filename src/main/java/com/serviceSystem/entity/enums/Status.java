package com.serviceSystem.entity.enums;

public enum Status {
    NOT_TAKEN("Не принят"),
    BEING_PERFORMED("Выполняется"),
    COMPLETED("Выполнен"),
    CANCELLED("Отменен");

    private String nameInRussian;

    Status(String nameInRussian){
        this.nameInRussian = nameInRussian;
    }

    public String getNameInRussian() {
        return nameInRussian;
    }
}
