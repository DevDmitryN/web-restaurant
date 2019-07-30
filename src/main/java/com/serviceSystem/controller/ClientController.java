package com.serviceSystem.controller;

import com.serviceSystem.controller.util.dtoConverter.DtoConverterImpl;
import com.serviceSystem.controller.util.dtoConverter.DtoConvertrer;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Qualifier(value = "clientSignUpValidator")
    @Autowired
    private Validator clientSignUpValidator;
    @Autowired
    private ClientService clientService;

    private DtoConvertrer<Client, ClientDto> clientDtoConverter = new DtoConverterImpl(Client.class, ClientDto.class);
    @GetMapping("/client/signUp")
    public String getSignUpPageForClient(Model model){
        model.addAttribute("client",new ClientDto());
        return "signUpClient";
    }
    @PostMapping("/client/signUp")
    public String signUpClient(@ModelAttribute("client") ClientDto clientDTO, BindingResult bindingResult, Model model){
        clientSignUpValidator.validate(clientDTO,bindingResult);
        if(bindingResult.hasErrors()){
            logger.info("Binding result client sign up " + bindingResult);
            return "signUpClient";
        }
        logger.info(clientDTO.toString());
        clientService.save(clientDtoConverter.fromDTO(clientDTO));
        return "redirect: /user/authorization";
    }
    @GetMapping(value = "/client/{id}")
    @ResponseBody
    public ClientDto getClient(@PathVariable("id") Long id){
        return clientDtoConverter.toDTO(clientService.getById(id));
    }
}
