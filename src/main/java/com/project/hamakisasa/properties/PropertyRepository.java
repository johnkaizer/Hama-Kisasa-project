package com.project.hamakisasa.properties;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLandlordId(String landlordId);
}
