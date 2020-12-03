package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherFetchService {

    private final WeatherRepository weatherRepository;




}
