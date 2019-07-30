package com.serviceSystem.service.validator;

import com.serviceSystem.controller.util.SignUpClientForm;
import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientSignUpValidator implements Validator {
    @Autowired
    private ClientService clientService;
    @Autowired
    private WorkerService workerService;

    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println(aClass);
        System.out.println(ClientDto.class.equals(aClass));
        return ClientDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpClientForm form = (SignUpClientForm) o;
        if (form.getName().length() == 0 || form.getName().length() > 35) {
            errors.rejectValue("name", "invalidName", "Длина имени должна быть от 1 до 35 символов");
        }
        if (form.getSurname().length() == 0 || form.getSurname().length() > 35) {
            errors.rejectValue("surname", "invalidSurname", "Длина фамилии должна быть от 1 до 35 символов");
        }
        if (form.getPassword().length() < 5 || form.getPassword().length() > 30) {
            errors.rejectValue("password", "invalidPassword", "Длина пароля должна быть от 5 до 30 символов");
        } else if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "doesntConfirm", "Пароли должны совпадать");
        }
        String regexForEmail = "\\A([\\w-]+)@([\\w-]+)[.](\\w{1,5})\\z";
        if (clientService.isEmailExist(form.getEmail()) || workerService.isEmailExist(form.getEmail())) {
            errors.rejectValue("email", "existedEmail", "Пользователь с таким адрессом уже существует");
        } else if (form.getEmail().length() == 0 || form.getEmail().length() > 60) {
            errors.rejectValue("email", "invalidEmail", "Длина адресса должна быть от 0 до 60 символов");
        } else if (!form.getEmail().matches(regexForEmail)) {
            errors.rejectValue("email", "invalidEmailFormat", "Неправильный формат адресса");
        }
        String regexForPhoneNumber = "\\A(44|29|25|17)(\\s|-)?\\d{3}(\\s|-)?\\d{2}(\\s|-)?\\d{2}\\z";
        if (form.getPhoneNumber().length() == 0) {
            errors.rejectValue("phoneNumber", "phoneNumberIsEmpty", "Номер телефона должен быть указан");
        } else if (clientService.isPhoneNumberExist(form.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "existedPhoneNumber", "Пользователь с таким номером телефона уже есть");
        } else if (!form.getPhoneNumber().matches(regexForPhoneNumber)) {
            errors.rejectValue("phoneNumber", "invalidPhoneNumber", "Не верный формат номера телефона");
        }
        String regexForCardNumber = "\\A([A-Z0-9]\\s?){16}\\z";
        if (form.getCardNumber().length() == 0) {
            errors.rejectValue("cardNumber", "cardNumberIsEmpty", "Номер карты должен быть указан");
        } else if (clientService.isCardNumberExist(form.getCardNumber())) {
            errors.rejectValue("cardNumber", "existedCardNumber", "Пользователь с такой картой уже существует");
        } else if (!form.getCardNumber().matches(regexForCardNumber)) {
            errors.rejectValue("cardNumber", "invalidCardNumber", "Не верный формат номера карты");
        }
    }
}
