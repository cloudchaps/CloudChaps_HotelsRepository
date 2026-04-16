package com.cloudchaps.properties.Properties.DTOs;

import java.time.LocalDate;
import java.util.List;

import com.cloudchaps.properties.Properties.enums.PropertyEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PropertiesDTO {
    private Long id;
    private String name;
    private String address;
    private PropertyEnum type;
    private String phone;
    private Double roomsAvailable;
    private Double rating;
    private String brand;
    private String description;
    public List<AmenitiesDTO> amenities;
    private LocalDate createdAt;

}