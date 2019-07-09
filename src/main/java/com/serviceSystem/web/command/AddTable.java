package com.serviceSystem.web.command;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.TableService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTable extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int capacity = Integer.valueOf(req.getParameter("capacity"));
        if(capacity <= 0){
            req.setAttribute("error","invalid capacity");
            req.getRequestDispatcher("addTable.jsp").forward(req,resp);
        }else{
            RestaurantTable table = new RestaurantTable();
            table.setCapacity(capacity);
            TableService.getInstance().save(table);
            resp.sendRedirect("tables.jsp");
        }
    }
}
