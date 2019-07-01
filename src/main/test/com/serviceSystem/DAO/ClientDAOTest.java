package com.serviceSystem.DAO;

import com.serviceSystem.service.ClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientDAOTest {
    @Test
    public void isClientExist(){
        assertTrue(ClientService.getInstance().isExist("+375447784545","1234"));
    }
    @Test
    public void doesntClientExist(){
        String phoneNumber = "+5446841321";
        String password = "0000";
        assertFalse(ClientService.getInstance().isExist(phoneNumber,password));
    }
    @Test
    public void incorrectPassword(){
        String phoneNumber = "+375447784545";
        String password = "0000";
        assertFalse(ClientService.getInstance().isExist(phoneNumber,password));
    }
}
