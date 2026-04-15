package com.cloudchaps.properties.Properties.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudchaps.properties.Properties.models.Properties;

public interface PropertiesRepository extends JpaRepository<Properties, Long> {} 