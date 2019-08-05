package com.serviceSystem.entity.dto.form;

import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.service.validation.MatchedPasswords;

import javax.validation.constraints.NotBlank;

@MatchedPasswords
public class SignUpClientForm extends ClientDto {
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
