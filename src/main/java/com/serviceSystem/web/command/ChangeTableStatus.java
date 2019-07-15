package com.serviceSystem.web.command;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeTableStatus extends Command {
    @Autowired
    TableService tableService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        int capacity = Integer.valueOf(req.getParameter("capacity"));
        boolean freeStatus = Boolean.valueOf(req.getParameter("status"));
        RestaurantTable table = new RestaurantTable();
        table.setId(id);
        table.setCapacity(capacity);
        table.setFreeStatus(freeStatus ? false : true);
        tableService.update(table);
        resp.sendRedirect("frontController?command=show_tables");
    }
}
