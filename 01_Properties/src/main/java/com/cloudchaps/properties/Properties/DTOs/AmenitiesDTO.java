package com.cloudchaps.properties.Properties.DTOs;

import java.math.BigDecimal;

import com.cloudchaps.properties.Properties.enums.AmenityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AmenitiesDTO {
    private String amenityName;
    private AmenityEnum amenityType;
    private String amenityDescription;
    private Boolean isIncluded;
    private BigDecimal amenityCost;
}
