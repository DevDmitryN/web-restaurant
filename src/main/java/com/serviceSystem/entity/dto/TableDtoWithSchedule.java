package com.serviceSystem.entity.dto;

import java.util.List;

public class TableDtoWithSchedule extends TableDto {
    private List<OrderedTime> schedule;

    public List<OrderedTime> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<OrderedTime> schedule) {
        this.schedule = schedule;
    }
}
