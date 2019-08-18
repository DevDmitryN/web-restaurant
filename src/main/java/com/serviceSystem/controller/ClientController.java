package com.serviceSystem.controller;

import com.serviceSystem.entity.dto.form.SignUpClientForm;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.entity.dto.form.UpdatePasswordForm;
import com.serviceSystem.exception.BindingResultException;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    private Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;


    @PostMapping(value = "/clients",consumes = "application/json")
    public ResponseEntity signUpClient(@RequestBody @Valid SignUpClientForm signUpClientForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.info("Binding result client sign up " + bindingResult);
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Client client = clientMapper.toEntity(signUpClientForm);
        clientService.save(clientMapper.toEntity(signUpClientForm));
        return new ResponseEntity<>(client,HttpStatus.CREATED);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("clientId") long id){
        return new ResponseEntity<>(clientMapper.toDto(clientService.getById(id)),HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getClients(){
        return new ResponseEntity<>(clientMapper.toDtoList(clientService.getAll()), HttpStatus.OK);
    }

    @PutMapping("/clients/{clientId}")
    public ResponseEntity updateClient(@PathVariable("clientId") long clientId,
                                       @RequestBody @Valid ClientDto clientDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Client client = clientMapper.toEntity(clientDto);
        clientService.updateExceptPassword(client);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PatchMapping("/clients/{clientId}")
    public ResponseEntity updatePassword(@PathVariable("clientId") long clientId,
                                         @Valid @RequestBody UpdatePasswordForm updatePasswordForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Client client = new Client();
        client.setId(clientId);
        client.setPassword(updatePasswordForm.getNewPassword());
        clientService.updateOnlyPassword(client);
        return new ResponseEntity(HttpStatus.OK);
    }

}
