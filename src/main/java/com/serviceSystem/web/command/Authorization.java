package com.serviceSystem.web.command;

import com.serviceSystem.entity.Client;
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
            SessionHandler.setUser(req.getSession(),workerService.getByEmail(email),"worker");
            resp.sendRedirect("index.jsp");
        }else {
            req.setAttribute("error",true);
            req.getRequestDispatcher("authorization.jsp").forward(req,resp);
//            resp.sendRedirect("authorization.jsp");
        }

//        String role = (String) req.getSession().getAttribute("role");
//        switch (role){
//            case "client": req.getRequestDispatcher("/").forward(req,resp); break;
//            case "worker": req.getRequestDispatcher("/").forward(req,resp); break;
//        }
    }
}
