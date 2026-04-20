package com.cloudchaps.properties.Properties.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cloudchaps.properties.Properties.DTOs.PropertiesDTO;
import com.cloudchaps.properties.Properties.services.PropertiesServiceImpl;

import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PropertiesController.class)
public class PropertiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PropertiesServiceImpl propertiesService;

    private String readJson(String path) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("File not found in classpath: " + path);
        }

        return new String(is.readAllBytes());

        // return new String(
        // new ClassPathResource(path).getInputStream().readAllBytes());
    }

    @Test
    void testCreateProperty_ValidProperty() throws Exception {

        String jsonRequest = readJson("mockFiles/Property.json");

        PropertiesDTO dto = objectMapper.readValue(jsonRequest, PropertiesDTO.class);

        //Mockito.when(propertiesService.createProperty(Mockito.any(PropertiesDTO.class)))
        //        .thenReturn(dto);

        Mockito.when(propertiesService.createProperty(Mockito.any(PropertiesDTO.class)))
       .thenAnswer(invocation -> {
           PropertiesDTO input = invocation.getArgument(0);
           input.setId(1L); // simulate DB-generated ID
           return input;
       });

        mockMvc.perform(
                post("/api/properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                // 🔥 Validate main fields
                .andExpect(jsonPath("$.name").value("Silene campanulata S. Watson"))
                .andExpect(jsonPath("$.address").value("59468 Annamark Place"))
                .andExpect(jsonPath("$.type").value("MOTEL"))
                .andExpect(jsonPath("$.phone").value("874-254-4458"))
                .andExpect(jsonPath("$.roomsAvailable").value(21))
                .andExpect(jsonPath("$.rating").value(9))
                .andExpect(jsonPath("$.brand").value("Lisinopril"))
                .andExpect(jsonPath("$.description")
                        .value("Variety pack of sticky notes in different colors and sizes."))

                // 🔥 Validate ID exists (since API adds it)
                .andExpect(jsonPath("$.id").exists())
                //.andExpect(jsonPath("$.id").isNumber());

                // 🔥 Validate amenities array
                .andExpect(jsonPath("$.amenities").isArray())
                .andExpect(jsonPath("$.amenities.length()").value(4))

                // 🔥 Validate first element (representative check)
                .andExpect(jsonPath("$.amenities[0].amenityName")
                        .value("Pseudalopex gymnocercus"))
                .andExpect(jsonPath("$.amenities[0].amenityType")
                        .value("PARKING"))
                .andExpect(jsonPath("$.amenities[0].amenityCost")
                        .value(3.99))

                // 🔥 Validate a boolean field (watch this 👇)
                .andExpect(jsonPath("$.amenities[0].isIncluded")
                        .value(true));
    }
}