package com.serviceSystem.service.validation;

import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingTimeValidator implements ConstraintValidator<ValidateBookingTime, OrderDto> {

    @Autowired
    private OrderService orderService;

    @Override
    public boolean isValid(OrderDto orderDto, ConstraintValidatorContext validatorContext) {
        if(orderDto.getBookingTimeBegin() == null || orderDto.getBookingTimeBegin().isEmpty()){
            validatorContext.buildConstraintViolationWithTemplate("Begin of booking time can't be blank")
                    .addPropertyNode("bookingTimeBegin").addConstraintViolation();
            return false;
        }
        if(orderDto.getBookingTimeEnd() == null || orderDto.getBookingTimeEnd().isEmpty()){
            validatorContext.buildConstraintViolationWithTemplate("End of booking time can't be blank")
                    .addPropertyNode("bookingTimeEnd").addConstraintViolation();
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bookingTimeBegin = LocalDateTime.parse(orderDto.getBookingTimeBegin(),formatter);
        LocalDateTime bookingTimeEnd = LocalDateTime.parse(orderDto.getBookingTimeEnd(),formatter);
        validatorContext.disableDefaultConstraintViolation();
        if(bookingTimeBegin.isBefore(now)){
            validatorContext.buildConstraintViolationWithTemplate(
                    "Begin of booking can't be in past"
            ).addPropertyNode("bookingTimeBegin").addConstraintViolation();
            return false;
        }
        if(bookingTimeEnd.isBefore(bookingTimeBegin)){
            validatorContext.buildConstraintViolationWithTemplate(
                    "End of booking time can't be before begin of booking"
            ).addPropertyNode("bookingTimeEnd").addConstraintViolation();
            return false;
        }
        if(!orderService.isBookingPeriodValidForTable(bookingTimeBegin,bookingTimeEnd,orderDto.getTable().getId())){
            validatorContext.buildConstraintViolationWithTemplate(
                    "Table " + orderDto.getTable().getId() + " already booked in this period"
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
