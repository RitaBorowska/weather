package com.sda.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Weather {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String temperature;
    String pressure;
    String humidity;
    String windDirection;
    String windSpeed;
}
