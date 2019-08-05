package com.serviceSystem.service.validation;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCardNumberValidator implements ConstraintValidator<UniqueCardNumber, ClientDto> {

    @Autowired
    private ClientService clientService;


    @Override
    public boolean isValid(ClientDto clientDto, ConstraintValidatorContext constraintValidatorContext) {
        Client client = clientService.getByCardNumber(clientDto.getCardNumber());
        if(client == null){
            return true;
        }
        return client.getId() == clientDto.getId();
    }
}
