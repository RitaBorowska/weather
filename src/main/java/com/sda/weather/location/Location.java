package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private Instant createDate;
    @CreatedBy
    private String createdBy;

    public Location(Long id, String nameCountry, String nameCity, String region, Double latitude, Double longitude) {
        this.id = id;
        this.nameCountry = nameCountry;
        this.nameCity = nameCity;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
