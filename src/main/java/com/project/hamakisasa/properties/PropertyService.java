package com.project.hamakisasa.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        property.setName(propertyDetails.getName());
        property.setLocation(propertyDetails.getLocation());
        property.setPrice(propertyDetails.getPrice());
        property.setBedrooms(propertyDetails.getBedrooms());
        property.setPropertyType(propertyDetails.getPropertyType());
        property.setDescription(propertyDetails.getDescription());
        property.setPhotos(propertyDetails.getPhotos());
        property.setAvailable(propertyDetails.isAvailable());
        property.setVerified(propertyDetails.isVerified());

        return propertyRepository.save(property);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    public List<Property> getAvailableProperties() {
        return propertyRepository.findByAvailable(true);
    }

    public List<Property> getPropertiesByType(PropertyType propertyType) {
        return propertyRepository.findByPropertyType(propertyType);
    }

    public List<Property> getPropertiesByLocation(String location) {
        return propertyRepository.findByLocation(location);
    }

    public List<Property> getPropertiesByLandlord(Long landlordId) {
        return propertyRepository.findByLandlord_Id(landlordId);
    }
}

