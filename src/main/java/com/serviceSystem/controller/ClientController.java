package com.serviceSystem.controller;

import com.serviceSystem.controller.form.SignUpClientForm;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.mapper.ClientMapper;
import com.serviceSystem.service.validator.ClientSignUpValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientSignUpValidator clientSignUpValidator;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;


    @PostMapping(value = "/clients",consumes = "application/json")
    public ResponseEntity signUpClient(@RequestBody SignUpClientForm signUpClientForm, BindingResult bindingResult){
        clientSignUpValidator.validate(signUpClientForm,bindingResult);
        if(bindingResult.hasErrors()){
            logger.info("Binding result client sign up " + bindingResult);
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        logger.info(signUpClientForm.toString());
        Client client = clientMapper.toEntity(signUpClientForm);
//        clientService.save(clientMapper.toEntity(signUpClientForm));
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id){
        return new ResponseEntity<>(clientMapper.toDto(clientService.getById(id)),HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getClients(){
        return new ResponseEntity<>(clientMapper.toDtoList(clientService.getAll()), HttpStatus.OK);
    }
}
