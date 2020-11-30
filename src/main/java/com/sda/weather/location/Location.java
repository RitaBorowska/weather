package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(nullable = false)
    private String nameCountry;
    @Column(nullable = false)
    private String nameCity;
    private String region;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
