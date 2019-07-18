package com.serviceSystem.controller;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;

    @GetMapping("/authorization")
    public String authorization(Model model, @ModelAttribute("error") String error,@ModelAttribute("logout") String logout){
//        if(error != null && !error.isEmpty()){
//            model.addAttribute("error","Email or password are invalid.");
//            return "authorization";
//        }
//        if(logout != null && !error.isEmpty()){
//            model.addAttribute("logout","You have been logged out successfully.");
//        }
        return "authorization";
    }

//    @GetMapping("/*")
//    public String mainPage(Model model, Principal user){
//        if(user != null){
//            SecurityContextHolderAwareRequestWrapper securityContex =
//            user.
//            Long userId =
//        }
//    }
}
