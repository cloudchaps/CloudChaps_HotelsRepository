package com.cloudchaps.properties.Properties.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "properties")
public class Properties {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name")
    private String name;

    @Column(name="property_address")
    private String address;

    @Column(name = "property_type")
    private String type;

    @Column(name = "property_phone")
    private String phone;

    @Column(name = "property_rooms")
    private Double roomsAvailable;

    @Column(name = "property_rating")
    private Double rating;

    @Column(name = "property_brand")
    private String brand;

    @Column(name = "property_description")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    //@Column(updatable = false)
    //private LocalDateTime createdAt;

    //@PrePersist
    //protected void onCreate() {
    //    createdAt = LocalDateTime.now();
    //}


}