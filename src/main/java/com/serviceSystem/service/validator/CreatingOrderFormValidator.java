package com.serviceSystem.service.validator;

import com.serviceSystem.controller.form.CreatingOrderForm;
import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Service
public class CreatingOrderFormValidator implements Validator {
    @Autowired
    private TableService tableService;
    @Autowired
    private OrderService orderService;
    @Override
    public boolean supports(Class<?> aClass) {
        return CreatingOrderForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreatingOrderForm creatingOrderForm = (CreatingOrderForm) o;
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(creatingOrderForm.getBookingTimeFromFields())){
            errors.rejectValue("bookingTime","afterRealTime","Время бронирования должно быть позже текущего времени");
        }
        boolean invalidAmountOfDishes = true;
        for (DishDto dish : creatingOrderForm.getDishes()) {
//            if(dish.getAmount() > 0 && dish.getAmount()<=20){
//                invalidAmountOfDishes = false;
//            }
        }
        if(invalidAmountOfDishes){
            errors.rejectValue("dishes","invalidAmount","В заказе должно быть от 1 до 20 блюд");
        }
        LocalDateTime nowPlusTwoHours = LocalDateTime.now().plusHours(2);
        if(!tableService.getById(creatingOrderForm.getTableId()).getFreeStatus() && creatingOrderForm.getBookingTimeFromFields().isBefore(nowPlusTwoHours)){

            errors.rejectValue("tables","bookingTakenTable","Бронировать занятый столик нужно минимум за 2 часа");
        }
    }
}
