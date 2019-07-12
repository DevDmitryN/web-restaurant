package com.serviceSystem.DAO;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.service.WorkerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerDAOTest {
    @Test
    public void getByEmail(){
        String email = "putin@rf.ru";
        Worker worker2 = WorkerService.getInstance().getByEmail(email);
        assertNotNull(worker2);
    }
    @Test
    public void isExistTrue(){
        String email = "putin@rf.ru";
        String password = "1234";
        assertTrue(WorkerService.getInstance().isExist(email,password));
    }
    @Test
    public void incorrectEmail(){
        String email = "lord@rig.net";
        String password = "1234";
        assertFalse(WorkerService.getInstance().isExist(email,password));
    }
    @Test
    public void incorrectPassword(){
        String email = "putin@rf.ru";
        String password = "0010";
        assertFalse(WorkerService.getInstance().isExist(email,password));
    }
    @Test
    void getById(){
        Integer id = 3;
        Worker worker = WorkerService.getInstance().getById(id);
        assertNotNull(worker);
    }
}
