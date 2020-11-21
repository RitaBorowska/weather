package com.sda.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Slf4j
public class ExceptionHandlerController  {

    @ExceptionHandler(BadRequestNoCountryOrCityNames.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestNoCountryOrCityNames(BadRequestNoCountryOrCityNames exception) {
        log.error(exception.getLocalizedMessage());
    }

    // todo handle NotFoundException
}
