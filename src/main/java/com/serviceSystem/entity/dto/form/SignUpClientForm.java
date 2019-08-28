package com.serviceSystem.entity.dto.form;

import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.service.validation.MatchedPasswords;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MatchedPasswords
public class SignUpClientForm extends ClientDto {
    @Size(min = 8,max = 50, message = "password must be between 8 and 50 characters ")
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
