package com.serviceSystem.entity;

import java.time.LocalTime;

public class Notification {
    private LocalTime sendingTime;
    private String content;

    public Notification(LocalTime sendingTime, String content) {
        this.sendingTime = sendingTime;
        this.content = content;
    }

    public LocalTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
