package com.cloudchaps.properties.Properties.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudchaps.properties.Properties.DTOs.PropertiesDTO;
import com.cloudchaps.properties.Properties.services.PropertiesServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/properties")
public class PropertiesController {

    @Autowired
    private PropertiesServiceImpl propertiesServiceImpl;

    @PostMapping("/batch") // :CHECK
    public ResponseEntity<List<PropertiesDTO>> createPropertiesList (@RequestBody List<PropertiesDTO> propertiesDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertiesServiceImpl.createPropertiesList(propertiesDTO));
    }

    @PostMapping // :CHECK
    public ResponseEntity<PropertiesDTO> createProperty(@RequestBody PropertiesDTO propertiesDTO)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertiesServiceImpl.createProperty(propertiesDTO));        
    }

    @DeleteMapping("/{id}") // : CHECK
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertiesServiceImpl.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PropertiesDTO>> getPropertiesList() {
        return ResponseEntity.ok(propertiesServiceImpl.getPropertiesList());
    }

    @GetMapping("/{id}") // : CHECK
    public ResponseEntity<PropertiesDTO> getProperty(@PathVariable Long id) {
        return ResponseEntity.ok(propertiesServiceImpl.getProperty(id));
    }

    @PutMapping("/{id}") // : CHECK
    public ResponseEntity<PropertiesDTO> updateProperty(@PathVariable Long id, @RequestBody PropertiesDTO propertiesDTO) {
        return ResponseEntity.ok(propertiesServiceImpl.updateProperty(id, propertiesDTO));
    }
       
    

}