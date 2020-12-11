package com.sda.weather.weather;

import com.sda.weather.exceptions.BadRequest;
import org.springframework.stereotype.Component;

@Component
public class WindDirection {


    private final static String[] DIRECTIONS = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};

    String mapWindDirection(int windDirection) {
        if (windDirection > 360 || windDirection < 0) {
            throw new BadRequest("the wind direction cannot be mapped: " + windDirection);
        }

        double val = Math.floor((windDirection / 22.5) + 0.5);
        return DIRECTIONS[(int) (val % 16)];
    }

}
