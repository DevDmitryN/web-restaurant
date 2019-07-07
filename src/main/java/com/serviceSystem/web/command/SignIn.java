package com.serviceSystem.web.command;

import com.serviceSystem.entity.Client;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignIn extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService clientService = ClientService.getInstance();
        WorkerService workerService = WorkerService.getInstance();
        String email = req.getParameter("email");
        if( !(workerService.isEmailExist(email) || clientService.isEmailExist(email))){
            clientService.save(buildClient(req));
            resp.sendRedirect("authorization.jsp");
        }else{
            req.setAttribute("error","error");
            req.getRequestDispatcher("signIn.jsp").forward(req,resp);
        }
    }
    private Client buildClient(HttpServletRequest req){
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");
        String cardNumber = req.getParameter("cardNumber");
        String email = req.getParameter("email");
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPassword(password);
        client.setPhoneNumber(phoneNumber);
        client.setCardNumber(cardNumber);
        return client;
    }
}
