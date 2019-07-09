package com.serviceSystem.DAO;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.service.WorkerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerDAOTest {
    @Test
    public void getByEmail(){
        long id = 3;
        String email = "putin@rf.ru";
        Worker worker1 = WorkerService.getInstance().getById(id);
        Worker worker2 = WorkerService.getInstance().getByEmail(email);
        System.out.println(worker1);
        assertEquals(worker1,worker2);
    }
    @Test
    public void isExistTrue(){
        String email = "lord@ring.net";
        String password = "0000";
        assertTrue(WorkerService.getInstance().isExist(email,password));
    }
    @Test
    public void incorrectEmail(){
        String email = "lord@rig.net";
        String password = "0000";
        assertFalse(WorkerService.getInstance().isExist(email,password));
    }
    @Test
    public void incorrectPassword(){
        String email = "lord@ring.net";
        String password = "0010";
        assertFalse(WorkerService.getInstance().isExist(email,password));
    }
}
