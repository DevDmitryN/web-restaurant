package com.serviceSystem.web.command;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowTables extends Command {
    @Autowired
    TableService tableService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RestaurantTable> tables = tableService.getAll();
        req.setAttribute("tables",tables);
        req.getRequestDispatcher("tables.jsp").forward(req,resp);
    }
}
