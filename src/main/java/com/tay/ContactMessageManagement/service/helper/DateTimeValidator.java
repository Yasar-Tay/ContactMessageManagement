package com.tay.ContactMessageManagement.service.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateTimeValidator {

    @ExceptionHandler
    public void validateDate(String givenDate){
        LocalDateTime dateTime = LocalDate.parse(givenDate).atTime(0,0);
    }
}
