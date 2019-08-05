package com.serviceSystem.service.validation;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.User;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.dto.UserDto;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserDto> {

    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        User user = clientService.getByEmail(userDto.getEmail());
        if (user != null) {
            return ((Client) user).getId() == userDto.getId();
        }
        user = workerService.getByEmail(userDto.getEmail());
        if (user != null) {
            return ((Worker) user).getId() == userDto.getId();
        }
        return true;
    }
}
