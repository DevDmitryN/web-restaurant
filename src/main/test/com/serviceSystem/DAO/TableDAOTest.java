package com.serviceSystem.DAO;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.TableService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TableDAOTest {
    @Test
    void getFree(){
        List<RestaurantTable> tables = TableService.getInstance().getFree();
        assertNotNull(tables);
    }
}
