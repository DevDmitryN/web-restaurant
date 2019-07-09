package com.serviceSystem.web.command;

import com.serviceSystem.entity.Client;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.regex.Pattern.matches;

public class SignUp extends Command {
    ClientService clientService = ClientService.getInstance();
    WorkerService workerService = WorkerService.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(isFieldsCorrect(req,resp)){
            clientService.save(buildClient(req));
            resp.sendRedirect("authorization.jsp");
        }
//        else{
//            req.setAttribute("error","error");
//            req.getRequestDispatcher("signUp.jsp").forward(req,resp);
//        }
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
    private boolean isFieldsCorrect(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if((workerService.isEmailExist(email) || clientService.isEmailExist(email))){
            req.setAttribute("error","existedEmail");
            req.getRequestDispatcher("signUp.jsp").forward(req,resp);
            return false;
        }
        String regexForPhoneNumber = "\\A(44|29|25|17)(\\s|-)?\\d{3}(\\s|-)?\\d{2}(\\s|-)?\\d{2}\\z";
        String regexForCardNumber = "\\A([A-Z0-9]\\s?){16}\\z";

        String phoneNumber = req.getParameter("phoneNumber");
        String cardNumber = req.getParameter("cardNumber");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        System.out.println((!(password.equals(confirmPassword)) + "" + (!matches(regexForPhoneNumber,phoneNumber)) + !matches(regexForCardNumber,cardNumber)));
        if(!(password.equals(confirmPassword) || !matches(regexForPhoneNumber,phoneNumber) || !matches(regexForCardNumber,cardNumber))){
            req.setAttribute("error","incorrectFields");
            req.getRequestDispatcher("signUp.jsp").forward(req,resp);
            return false;
        }
        return  true;
    }
}
