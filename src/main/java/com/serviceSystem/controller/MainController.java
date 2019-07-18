package com.serviceSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller

public class MainController {

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
//    @GetMapping("/logout")
//    public String logout(){
//        return "redirect: authorization";
//    }
}
