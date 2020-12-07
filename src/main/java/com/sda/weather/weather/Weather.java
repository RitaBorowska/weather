package com.sda.weather.weather;

import com.sda.weather.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Instant createDate;
    Instant weatherDate;
    String temperature;
    String pressure;
    String humidity;
    String windDirection;
    String windSpeed;

    @ManyToOne
    private Location location;
}
