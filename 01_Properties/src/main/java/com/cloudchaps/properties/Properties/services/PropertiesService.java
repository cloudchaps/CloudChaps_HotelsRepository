package com.cloudchaps.properties.Properties.services;

import java.util.List;

import com.cloudchaps.properties.Properties.DTOs.PropertiesDTO;

public interface PropertiesService {
    List<PropertiesDTO> getPropertiesList();
    List<PropertiesDTO> createPropertiesList(List<PropertiesDTO> dtos);
    PropertiesDTO updateProperty(Long id, PropertiesDTO propertiesDTO);
    PropertiesDTO getProperty(Long id);
    PropertiesDTO createProperty(PropertiesDTO propertiesDTO);
    void deleteProperty(Long id);
    
}