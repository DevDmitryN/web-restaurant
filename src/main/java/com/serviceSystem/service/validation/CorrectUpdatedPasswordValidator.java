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
    private String message;

    @Override
    public void initialize(CorrectUpdatedPassword constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UpdatePasswordForm updatePasswordForm, ConstraintValidatorContext constraintValidatorContext) {
        User user = clientService.getByEmail(updatePasswordForm.getEmail());
        if(user != null){
            if(!encoder.matches(updatePasswordForm.getOldPassword(),user.getPassword())){
                message = "old password doesn't match";
                return false;
            }
            if(updatePasswordForm.getNewPassword().equals(updatePasswordForm.getConfirmNewPassword())){

            }
        }
        user = workerService.getByEmail(updatePasswordForm.getEmail());
        if(user != null){
            return encoder.matches(updatePasswordForm.getOldPassword(),user.getPassword())
                    && updatePasswordForm.getNewPassword().equals(updatePasswordForm.getConfirmNewPassword());
        }
        return false;
    }
}
