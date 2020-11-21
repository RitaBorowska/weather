package com.sda.weather.exceptions;

public class BadRequestNoCountryOrCityNames extends RuntimeException{

    public BadRequestNoCountryOrCityNames(String message){
        super("Bad request" + message);
    }
}
