package com.serviceSystem.controller;

import com.serviceSystem.controller.util.dtoConverter.DtoConverterImpl;
import com.serviceSystem.controller.util.dtoConverter.DtoConvertrer;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.DTO.ClientDTO;
import com.serviceSystem.service.ClientService;
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
import java.security.Principal;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);



//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.setValidator(clientSignUpValidator);
//        binder.addValidators(clientSignUpValidator);
//    }
    @GetMapping("/user/authorization")
    public String getAuthorizationPage(Model model,@ModelAttribute("error") String error){
        if(error!=null && !error.isEmpty()){
            model.addAttribute(error,error);
        }
        return "authorization";
    }

}
