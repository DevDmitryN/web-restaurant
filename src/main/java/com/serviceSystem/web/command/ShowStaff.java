package com.serviceSystem.web.command;

import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowStaff extends Command {
    @Autowired
    WorkerService workerService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("workers", workerService.getAll());
        req.getRequestDispatcher("showStaff.jsp").forward(req,resp);
    }
}
