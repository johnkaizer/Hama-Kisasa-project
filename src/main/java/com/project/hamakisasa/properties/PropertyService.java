package com.project.hamakisasa.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property addProperty(String name, String location, BigDecimal price, String propertyType, String description, MultipartFile image, boolean status, String landlordId) {
        Property property = new Property();
        property.setName(name);
        property.setLocation(location);
        property.setPrice(price);
        property.setPropertyType(propertyType);
        property.setDescription(description);
        try {
            property.setImage(image.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image.", e);
        }
        property.setStatus(status);
        property.setLandlordId(landlordId);
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> filterProperties(BigDecimal minPrice, BigDecimal maxPrice, String location, String propertyType) {
        return propertyRepository.findAll().stream()
                .filter(property -> (minPrice == null || property.getPrice().compareTo(minPrice) >= 0))
                .filter(property -> (maxPrice == null || property.getPrice().compareTo(maxPrice) <= 0))
                .filter(property -> (location == null || property.getLocation().equalsIgnoreCase(location)))
                .filter(property -> (propertyType == null || property.getPropertyType().equalsIgnoreCase(propertyType)))
                .collect(Collectors.toList());
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        Property existingProperty = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found."));
        existingProperty.setName(updatedProperty.getName());
        existingProperty.setLocation(updatedProperty.getLocation());
        existingProperty.setPrice(updatedProperty.getPrice());
        existingProperty.setPropertyType(updatedProperty.getPropertyType());
        existingProperty.setDescription(updatedProperty.getDescription());
        existingProperty.setStatus(updatedProperty.isStatus());
        existingProperty.setLandlordId(updatedProperty.getLandlordId());
        if (updatedProperty.getImage() != null) {
            existingProperty.setImage(updatedProperty.getImage());
        }
        return propertyRepository.save(existingProperty);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    public List<Property> getPropertiesByLandlordId(String landlordId) {
        return propertyRepository.findByLandlordId(landlordId);
    }
    public long getTotalProperties() {
        return propertyRepository.count();
    }
}
