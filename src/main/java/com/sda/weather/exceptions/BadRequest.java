package com.sda.weather.exceptions;

public class BadRequest extends RuntimeException{

    public BadRequest(String message){
        super("Bad request " + message);
    }
}
