package com.serviceSystem.entity.dto.form;

import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.service.validation.MatchedPasswords;

import javax.validation.constraints.NotBlank;

@MatchedPasswords
public class SignUpWorkerForm extends WorkerDto {

    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
