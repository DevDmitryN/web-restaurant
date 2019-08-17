package com.serviceSystem.controller;

import com.serviceSystem.entity.Notification;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TestWebSocketController {

    @MessageMapping("/notification")
    @SendTo("/topic/notification")
    public Notification sendMessage(Notification notification){

        return notification;
    }
}
