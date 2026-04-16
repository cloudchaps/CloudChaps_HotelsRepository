package com.cloudchaps.properties.Properties.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudchaps.properties.Properties.DTOs.AmenitiesDTO;
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
    public PropertiesDTO updateProperty(Long id, PropertiesDTO dto) {

        Properties existingProperty = propertiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));

        // update scalar fields
        existingProperty.setName(dto.getName());
        existingProperty.setAddress(dto.getAddress());
        existingProperty.setType(dto.getType());
        existingProperty.setPhone(dto.getPhone());
        existingProperty.setRoomsAvailable(dto.getRoomsAvailable());
        existingProperty.setRating(dto.getRating());
        existingProperty.setBrand(dto.getBrand());
        existingProperty.setDescription(dto.getDescription());

        // 🔥 MAP EXISTING amenities by ID
        Map<Long, Amenities> existingMap = existingProperty.getAmenities()
                .stream()
                .collect(Collectors.toMap(Amenities::getId, a -> a));

        List<Amenities> updatedAmenities = new ArrayList<>();

        for (AmenitiesDTO aDto : dto.getAmenities()) {

            Amenities amenity;

            if (aDto.getId() != null && existingMap.containsKey(aDto.getId())) {
                // ✅ UPDATE existing
                amenity = existingMap.get(aDto.getId());
            } else {
                // ✅ CREATE new
                amenity = new Amenities();
                amenity.setProperty(existingProperty);
            }

            amenity.setAmenityName(aDto.getAmenityName());
            amenity.setAmenityDescription(aDto.getAmenityDescription());
            amenity.setAmenityType(aDto.getAmenityType());
            amenity.setIsIncluded(aDto.getIsIncluded());
            amenity.setAmenityCost(aDto.getAmenityCost());

            updatedAmenities.add(amenity);
        }

        // 🔥 THIS triggers orphan removal for deleted ones
        existingProperty.getAmenities().clear();
        existingProperty.getAmenities().addAll(updatedAmenities);

        Properties saved = propertiesRepository.save(existingProperty);

        return PropertiesMapper.toDto(saved);
    }

}