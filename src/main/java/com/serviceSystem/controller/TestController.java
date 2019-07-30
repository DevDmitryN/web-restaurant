package com.serviceSystem.controller;

import com.serviceSystem.entity.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @ResponseBody
    @GetMapping("/test-client")
    public Client getTestClient(){
        Client client = new Client();
        client.setName("name");
        client.setSurname("surname");
        client.setEmail("email");
        return client;
    }

    @GetMapping("/test-list-client")
    public List<Client> getClients(){
        List<Client> list = new ArrayList<>();
        Client client1 = new Client();
        client1.setName("client1");
        Client client2 = new Client();
        client2.setName("client2");
        Client client3 = new Client();
        client3.setName("client3");
        list.add(client1);
        list.add(client2);
        list.add(client3);
        return list;
    }
}
