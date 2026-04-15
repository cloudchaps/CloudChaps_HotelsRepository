package com.cloudchaps.properties.Properties.mappers;

import com.cloudchaps.properties.Properties.DTOs.PropertiesDTO;
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
        //p.setCreatedAt(LocalDateTime.now());

        return p;

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
        //pd.setCreatedAt(properties.getCreatedAt().toLocalDate());

        return pd;
    }

}