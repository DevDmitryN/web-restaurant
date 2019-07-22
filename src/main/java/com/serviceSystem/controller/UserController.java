package com.serviceSystem.controller;

import com.serviceSystem.entity.DTO.ClientDTO;
import com.serviceSystem.service.validator.ClientSignUpValidator;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Qualifier(value = "clientSignUpValidator")
    @Autowired
    private Validator clientSignUpValidator;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.setValidator(clientSignUpValidator);
//        binder.addValidators(clientSignUpValidator);
//    }
    @GetMapping("authorization")
    public String getAuthorizationPage(Model model,@ModelAttribute("error") String error){
        if(error!=null && !error.isEmpty()){
            model.addAttribute(error,error);
        }
        return "authorization";
    }
    @GetMapping("signUpForClient")
    public String getSignUpPageForClient(Model model){
        model.addAttribute("client",new ClientDTO());
        return "signUp";
    }
    @PostMapping("signUpForClient")
    public String signUpClient(@ModelAttribute("client") ClientDTO clientDTO, BindingResult bindingResult, Model model){
        clientSignUpValidator.validate(clientDTO,bindingResult);
        if(bindingResult.hasErrors()){
            logger.info("Binding result client sign up " + bindingResult);
            return "signUp";
        }
        logger.info(clientDTO.toString());
        return "authorization";
    }
}
