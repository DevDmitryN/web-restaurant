package com.serviceSystem.DAO;

import com.serviceSystem.config.ApplicationConfig;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class TableDAOTest {
    @Autowired
    TableService tableService;
    @Test
    public void getFree(){
        List<RestaurantTable> tables = tableService.getFree();
        assertNotNull(tables);
    }
    @Test
    public void getAll(){
        List<RestaurantTable> tables = tableService.getAll();
        assertNotNull(tables);
        tables.forEach( table -> assertNotNull(table));
    }
}
