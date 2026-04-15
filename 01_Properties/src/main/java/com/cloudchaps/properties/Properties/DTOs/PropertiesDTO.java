package com.cloudchaps.properties.Properties.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PropertiesDTO {
    private Long id;
    private String name;
    private String address;
    private String type;
    private String phone;
    private Double roomsAvailable;
    private Double rating;
    private String brand;
    private String description;
    private LocalDate createdAt;

}