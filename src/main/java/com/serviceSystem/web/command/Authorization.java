package com.serviceSystem.web.command;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Role;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import com.serviceSystem.web.filter.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorization extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService clientService = ClientService.getInstance();
        WorkerService workerService = WorkerService.getInstance();

        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        if(clientService.isExist(email,password)){
            SessionHandler.setUser(req.getSession(),clientService.getByEmail(email),"client");
            resp.sendRedirect("index.jsp");
        }else if(workerService.isExist(email,password)){
            Worker worker = workerService.getByEmail(email);
            switch (worker.getRole()){
                case USER:
                    SessionHandler.setUser(req.getSession(),worker,"worker");
                    resp.sendRedirect("index.jsp");
                    break;
                case ADMIN:
                    SessionHandler.setUser(req.getSession(),worker,"admin");
                    resp.sendRedirect("index.jsp");
                    break;
            }
        }else {
            req.setAttribute("error",true);
            req.getRequestDispatcher("authorization.jsp").forward(req,resp);
        }
    }
}
