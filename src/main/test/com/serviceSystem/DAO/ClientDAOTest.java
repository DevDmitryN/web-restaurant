package com.serviceSystem.DAO;

import com.serviceSystem.entity.Client;
import com.serviceSystem.hibernate.Config;
import com.serviceSystem.service.ClientService;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
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
    @Test
    public void getAll(){
        List<Client> clients = ClientService.getInstance().getAll();
        assertNotNull(clients);
        clients.forEach(client -> assertNotNull(client));
    }

}
