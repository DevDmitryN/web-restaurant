package com.serviceSystem.DAO;

import com.serviceSystem.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientDAOTest {
    @Test
    public void isExistTrue(){
        String email = "boris@britva.com";
        String password = "1234";
        assertTrue(ClientService.getInstance().isExist(email,password));
    }
    @Test
    public void incorrectEmail(){
        String email = "boris@ritva.com";
        String password = "1234";
        assertFalse(ClientService.getInstance().isExist(email,password));
    }
    @Test
    public void incorrectPassword(){
        String phoneNumber = "boris@britva.com";
        String password = "0000";
        assertFalse(ClientService.getInstance().isExist(phoneNumber,password));
    }

}
