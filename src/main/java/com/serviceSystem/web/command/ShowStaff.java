package com.serviceSystem.web.command;

import com.serviceSystem.service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowStaff extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("workers", WorkerService.getInstance().getAll());
        req.getRequestDispatcher("showStaff.jsp").forward(req,resp);
    }
}
