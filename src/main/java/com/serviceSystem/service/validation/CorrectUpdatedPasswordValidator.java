package com.serviceSystem.service.validation;

import com.serviceSystem.entity.User;
import com.serviceSystem.entity.dto.UserDto;
import com.serviceSystem.entity.dto.form.UpdatePasswordForm;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectUpdatedPasswordValidator implements ConstraintValidator<CorrectUpdatedPassword, UpdatePasswordForm>{

    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private BCryptPasswordEncoder encoder;



    @Override
    public boolean isValid(UpdatePasswordForm updatePasswordForm, ConstraintValidatorContext constraintValidatorContext) {
        User user = clientService.getByEmail(updatePasswordForm.getEmail());
        if(user == null){
            user =  workerService.getByEmail(updatePasswordForm.getEmail());
        }
        if(user != null){
            if(!encoder.matches(updatePasswordForm.getOldPassword(),user.getPassword())){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Incorrect old password").addConstraintViolation();
                return false;
            }
            if(!updatePasswordForm.getNewPassword().equals(updatePasswordForm.getConfirmNewPassword())){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("New password and confirmation don't match");
                return false;
            }
        }
        return false;
    }
}
