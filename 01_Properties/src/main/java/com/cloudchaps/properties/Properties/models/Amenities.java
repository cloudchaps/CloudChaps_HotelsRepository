package com.cloudchaps.properties.Properties.models;

import java.math.BigDecimal;

import com.cloudchaps.properties.Properties.enums.AmenityEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "amenities")
public class Amenities {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Properties property;

    @Column(name = "amenity_name")
    private String amenityName;

    @Column(name = "amenity_description")
    private String amenityDescription;

    @Column(name = "amenity_type") @Enumerated(EnumType.STRING)
    private AmenityEnum amenityType;

    @Column(name = "is_included")
    private Boolean isIncluded;

    @Column(name = "amenity_cost")
    private BigDecimal amenityCost;
    
}
