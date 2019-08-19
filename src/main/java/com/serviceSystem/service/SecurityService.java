package com.serviceSystem.service;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Order;
import com.serviceSystem.exception.NoSuchItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;

    public boolean validClientId(Authentication authentication, long clientId){
        Client client = clientService.getByEmail(authentication.getName());
        return client != null && client.getId() == clientId;
    }
    public boolean hasAccessToOrder(Authentication authentication, long orderId){
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("WAITER"))) {
            return true;
        }
        try {
            Order order = orderService.getById(orderId);
            return order != null && order.getClient().getEmail().equals(authentication.getName());
        }catch (NoSuchItemException e){
            return false;
        }
    }
}
