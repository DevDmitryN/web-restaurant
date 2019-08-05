package com.serviceSystem.service.validation;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.entity.dto.UserDto;
import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.entity.dto.form.SignUpClientForm;
import com.serviceSystem.entity.enums.UserRole;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, UserDto> {

    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;
    private long id;
    private UserRole role;

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        if(userDto instanceof ClientDto){
            Client client = clientService.getByPhoneNumber(userDto.getPhoneNumber());
            if(client == null){
                return true;
            }
            return client.getId() == userDto.getId();
        }
        if(userDto instanceof WorkerDto){
            Worker worker = workerService.getByPhoneNumber(userDto.getPhoneNumber());
            if(worker == null){
                return true;
            }
            return worker.getId() == userDto.getId();
        }
        return true;
    }
}
