package com.serviceSystem.entity.dto;


import com.serviceSystem.service.validation.UniqueCardNumber;
import com.serviceSystem.service.validation.UniqueEmail;
import com.serviceSystem.service.validation.UniquePhoneNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@UniqueCardNumber
public class ClientDto extends UserDto{

    @Size(min = 1,max = 50,message = "Name must be between 1 and 50 characters")
    @Pattern(regexp = "\\A([A-Z0-9]\\s?){16}\\z",message = "Incorrect card number")
    private String cardNumber;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
