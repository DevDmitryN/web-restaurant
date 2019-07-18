package com.serviceSystem.DAO;

import com.serviceSystem.appConfig.*;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.service.WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class, WebConfig.class, WebInitializer.class, SpringSecurityInitializer.class})
public class WorkerDAOTest {
    @Autowired
    WorkerService workerService;
    @Test
    public void getByEmail(){
        String email = "putin@rf.ru";
        Worker worker2 = workerService.getByEmail(email);
        assertNotNull(worker2);
    }
    @Test
    public void isExistTrue(){
        String email = "putin@rf.ru";
        String password = "1234";
        assertTrue(workerService.isExist(email,password));
    }
    @Test
    public void incorrectEmail(){
        String email = "lord@rig.net";
        String password = "1234";
        assertFalse(workerService.isExist(email,password));
    }
    @Test
    public void incorrectPassword(){
        String email = "putin@rf.ru";
        String password = "0010";
        assertFalse(workerService.isExist(email,password));
    }
    @Test
    public void getById(){
        Integer id = 3;
        Worker worker = workerService.getById(id);
        assertNotNull(worker);
    }
//    @Test
//    public void updatePassword(){
//        Worker worker = workerService.getByEmail("putin@rf.ru");
//        worker.setPassword("0000");
//        workerService.update(worker);
//    }
}
