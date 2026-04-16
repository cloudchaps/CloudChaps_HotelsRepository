package com.cloudchaps.properties.Properties.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.cloudchaps.properties.Properties.enums.PropertyEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(name = "property_type") @Enumerated(EnumType.STRING)
    private PropertyEnum type;

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

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Amenities> amenities;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    //@Column(updatable = false)
    //private LocalDateTime createdAt;

    //@PrePersist
    //protected void onCreate() {
    //    createdAt = LocalDateTime.now();
    //}


}