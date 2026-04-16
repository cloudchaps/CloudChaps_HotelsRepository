package com.cloudchaps.properties.Properties.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudchaps.properties.Properties.DTOs.PropertiesDTO;
import com.cloudchaps.properties.Properties.mappers.PropertiesMapper;
import com.cloudchaps.properties.Properties.models.Amenities;
import com.cloudchaps.properties.Properties.models.Properties;
import com.cloudchaps.properties.Properties.repositories.PropertiesRepository;
import java.util.stream.Collectors;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Override
    public List<PropertiesDTO> createPropertiesList(List<PropertiesDTO> dtos) {
        List<Properties> entities = dtos.stream()
        .map(PropertiesMapper::toEntity)
        .collect(Collectors.toList());
        return propertiesRepository.saveAll(entities).stream()
        .map(PropertiesMapper::toDto)
        .collect(Collectors.toList());
    }

    @Override
    public PropertiesDTO createProperty(PropertiesDTO propertiesDTO) {
        Properties savedProperty = propertiesRepository.save(PropertiesMapper.toEntity(propertiesDTO));
        return PropertiesMapper.toDto(savedProperty);
    }

    @Override
    public void deleteProperty(Long id) {
        propertiesRepository.deleteById(id);
        
    }

    @Override
    public List<PropertiesDTO> getPropertiesList() {
        return propertiesRepository.findAll().stream()
        .map(PropertiesMapper::toDto)
        .collect(Collectors.toList());
    }

    @Override
    public PropertiesDTO getProperty(Long id) {
        return propertiesRepository.findById(id)
        .map(PropertiesMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    @Override
    public PropertiesDTO updateProperty(Long id, PropertiesDTO propertiesDTO) {
        Properties existingProperty = propertiesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));

        existingProperty.setName(propertiesDTO.getName());
        existingProperty.setAddress(propertiesDTO.getAddress());
        existingProperty.setAddress(propertiesDTO.getAddress());
        existingProperty.setType(propertiesDTO.getType());
        existingProperty.setPhone(propertiesDTO.getPhone());
        existingProperty.setRoomsAvailable(propertiesDTO.getRoomsAvailable());
        existingProperty.setRating(propertiesDTO.getRating());
        existingProperty.setBrand(propertiesDTO.getBrand());
        existingProperty.setDescription(propertiesDTO.getDescription());
        List<Amenities> amenities = propertiesDTO.amenities != null ? propertiesDTO.amenities.stream()
            .map(a -> {
                Amenities amenity = new Amenities();
                amenity.setAmenityName(a.getAmenityName());
                amenity.setAmenityDescription(a.getAmenityDescription());
                amenity.setAmenityType(a.getAmenityType());
                amenity.setIsIncluded(a.getIsIncluded());
                amenity.setAmenityCost(a.getAmenityCost());
                amenity.setProperty(existingProperty);
                return amenity;
            })
            .collect(Collectors.toList()) : Collections.emptyList();
        existingProperty.setAmenities(amenities);



        Properties updatedProperty = propertiesRepository.save(existingProperty);

        return PropertiesMapper.toDto(updatedProperty);
    }

}