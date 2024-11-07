package com.project.hamakisasa.properties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByAvailable(boolean available);

    List<Property> findByPropertyType(PropertyType propertyType);

    List<Property> findByLocation(String location);

    List<Property> findByLandlord_Id(Long landlordId);
}

