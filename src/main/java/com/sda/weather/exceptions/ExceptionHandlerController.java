package com.sda.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Slf4j
public class ExceptionHandlerController  {

    @ExceptionHandler(BadRequest.class)     // todo ConstraintViolationException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestNoCountryOrCityNames(BadRequest exception) {
        log.error(exception.getLocalizedMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void runtimeException(RuntimeException exception){
        log.error(exception.getMessage());
    }
}
