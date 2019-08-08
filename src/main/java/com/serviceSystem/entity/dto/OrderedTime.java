package com.serviceSystem.entity.dto;

import java.time.LocalDateTime;

public class OrderedTime {
    private String begin;
    private String end;

    public OrderedTime(){}

    public OrderedTime(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
