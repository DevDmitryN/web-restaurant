package com.serviceSystem.DAO;

import com.serviceSystem.appConfig.ApplicationConfig;
import com.serviceSystem.entity.Client;
import com.serviceSystem.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ClientDAOTest {

    @Autowired
    ClientService clientService;

    @Test
    public void isExistTrue(){
        String email = "boris@britva.com";
        String password = "1234";
        assertTrue(clientService.isExist(email,password));
    }
    @Test
    public void incorrectEmail(){
        String email = "boris@ritva.com";
        String password = "1234";
        assertFalse(clientService.isExist(email,password));
    }
    @Test
    public void incorrectPassword(){
        String phoneNumber = "boris@britva.com";
        String password = "0000";
        assertFalse(clientService.isExist(phoneNumber,password));
    }
    @Test
    public void getAll(){
        List<Client> clients = clientService.getAll();
        assertNotNull(clients);
        clients.forEach(client -> assertNotNull(client));
    }

}
