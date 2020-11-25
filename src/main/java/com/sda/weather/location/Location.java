package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCountry;
    private String nameCity;
    private String region;
    private Double latitude;
    private Double longitude;

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
