package com.cloudchaps.properties.Properties.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.cloudchaps.properties.Properties.DTOs.AmenitiesDTO;
import com.cloudchaps.properties.Properties.DTOs.PropertiesDTO;
import com.cloudchaps.properties.Properties.models.Amenities;
import com.cloudchaps.properties.Properties.models.Properties;

public class PropertiesMapper {

    public static Properties toEntity(PropertiesDTO propertiesDTO) {
        if (propertiesDTO == null) return null;

        Properties p = new Properties();
        p.setName(propertiesDTO.getName());
        p.setAddress(propertiesDTO.getAddress());
        p.setType(propertiesDTO.getType());
        p.setPhone(propertiesDTO.getPhone());
        p.setRoomsAvailable(propertiesDTO.getRoomsAvailable());
        p.setRating(propertiesDTO.getRating());
        p.setBrand(propertiesDTO.getBrand());
        p.setDescription(propertiesDTO.getDescription());

        List<Amenities> amenities = propertiesDTO.amenities != null ? propertiesDTO.amenities.stream()
            .map(a -> {
                Amenities amenity = new Amenities();
                amenity.setAmenityName(a.getAmenityName());
                amenity.setAmenityDescription(a.getAmenityDescription());
                amenity.setAmenityType(a.getAmenityType());
                amenity.setIsIncluded(a.getIsIncluded());
                amenity.setAmenityCost(a.getAmenityCost());
                amenity.setProperty(p);
                return amenity;
            })
            .collect(Collectors.toList()) : Collections.emptyList();
        p.setAmenities(amenities);
        return p;
        //p.setCreatedAt(LocalDateTime.now());

    }

    public static PropertiesDTO toDto(Properties properties) {
        if (properties == null) return null;

        PropertiesDTO pd = new PropertiesDTO();
        pd.setId(properties.getId());
        pd.setName(properties.getName());
        pd.setAddress(properties.getAddress());
        pd.setType(properties.getType());
        pd.setPhone(properties.getPhone());
        pd.setRoomsAvailable(properties.getRoomsAvailable());
        pd.setRating(properties.getRating());
        pd.setBrand(properties.getBrand());
        pd.setDescription(properties.getDescription());
        pd.setAmenities(properties.getAmenities() != null ? properties.getAmenities().stream()
            .map(a -> {
                AmenitiesDTO amenityDTO = new AmenitiesDTO();
                amenityDTO.setAmenityName(a.getAmenityName());
                amenityDTO.setAmenityDescription(a.getAmenityDescription());
                amenityDTO.setAmenityType(a.getAmenityType());
                amenityDTO.setIsIncluded(a.getIsIncluded());
                amenityDTO.setAmenityCost(a.getAmenityCost());
                return amenityDTO;
            })
            .collect(Collectors.toList()) : Collections.emptyList());
        
        //pd.setCreatedAt(properties.getCreatedAt().toLocalDate());

        return pd;
    }

}