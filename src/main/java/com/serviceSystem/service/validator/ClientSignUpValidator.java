package com.serviceSystem.service.validator;

import com.serviceSystem.entity.DTO.ClientDTO;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ClientSignUpValidator implements Validator {
    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;

    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println(aClass);
        System.out.println(ClientDTO.class.equals(aClass));
        return ClientDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ClientDTO client = (ClientDTO) o;
        if (client.getName().length() == 0 || client.getName().length() > 35) {
            errors.rejectValue("name", "invalidName", "Длина имени должна быть от 1 до 35 символов");
        }
        if (client.getSurname().length() == 0 || client.getSurname().length() > 35) {
            errors.rejectValue("surname", "invalidSurname", "Длина фамилии должна быть от 1 до 35 символов");
        }
        if (client.getPassword().length() < 5 || client.getPassword().length() > 30) {
            errors.rejectValue("password", "invalidPassword", "Длина пароля должна быть от 5 до 30 символов");
        } else if (!client.getPassword().equals(client.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "doesntConfirm", "Пароли должны совпадать");
        }
        String regexForEmail = "([\\d\\w-_]+)@([\\d\\w-_]+)[.](\\w{1,5})";
        if (clientService.isEmailExist(client.getEmail()) || workerService.isEmailExist(client.getEmail())) {
            errors.rejectValue("email", "existedEmail", "Пользователь с таким адрессом уже существует");
        } else if (client.getEmail().length() == 0 || client.getEmail().length() > 60) {
            errors.rejectValue("email", "invalidEmail", "Длина адресса должна быть от 0 до 60 символов");
        } else if(client.getEmail().matches(regexForEmail)){
            errors.rejectValue("email","invalidEmailFormat","Неправильный формат адресса");
        }
        String regexForPhoneNumber = "\\A(44|29|25|17)(\\s|-)?\\d{3}(\\s|-)?\\d{2}(\\s|-)?\\d{2}\\z";
        if (client.getPhoneNumber().length() == 0) {
            errors.rejectValue("phoneNumber", "phoneNumberIsEmpty", "Номер телефона должен быть указан");
        } else if (clientService.isPhoneNumberExist(client.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "existedPhoneNumber", "Пользователь с таким номером телефона уже есть");
        } else if (!client.getPhoneNumber().matches(regexForPhoneNumber)) {
            errors.rejectValue("phoneNumber", "invalidPhoneNumber", "Не верный формат номера телефона");
        }
        String regexForCardNumber = "\\A([A-Z0-9]\\s?){16}\\z";
        if (client.getCardNumber().length() == 0) {
            errors.rejectValue("cardNumber", "cardNumberIsEmpty", "Номер карты должен быть указан");
        } else if (clientService.isCardNumberExist(client.getCardNumber())) {
            errors.rejectValue("cardNumber", "existedCardNumber", "Пользователь с такой картой уже существует");
        } else if (!client.getCardNumber().matches(regexForCardNumber)) {
            errors.rejectValue("cardNumber", "invalidCardNumber", "Не верный формат номера карты");
        }
    }
}
