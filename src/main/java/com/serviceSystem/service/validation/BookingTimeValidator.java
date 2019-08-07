package com.serviceSystem.service.validation;

import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingTimeValidator implements ConstraintValidator<ValidateBookingTime, OrderDto> {

    @Autowired
    private BookingService bookingService;

    @Override
    public boolean isValid(OrderDto orderDto, ConstraintValidatorContext validatorContext) {
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
        if(!bookingService.isBookingPeriodValidForTable(bookingTimeBegin,bookingTimeEnd,orderDto.getTable().getId())){
            validatorContext.buildConstraintViolationWithTemplate(
                    "Table already booked in this period"
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}