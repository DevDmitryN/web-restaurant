package com.serviceSystem.entity.dto;

import java.time.LocalDateTime;

public class OrderedTime {
    private LocalDateTime begin;
    private LocalDateTime end;

    public OrderedTime(){}
    public OrderedTime(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
