package com.project.hamakisasa.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Add a new property
    @PostMapping
    public ResponseEntity<Property> addProperty(
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam BigDecimal price,
            @RequestParam String propertyType,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam boolean status,
            @RequestParam String landlordId
    ) {
        Property property = propertyService.addProperty(name, location, price, propertyType, description, image, status, landlordId);
        return ResponseEntity.ok(property);
    }

    // Fetch all properties
    @GetMapping
    public List<Property> getAllProperties() {
        // Fetch all properties from the service
        return propertyService.getAllProperties();
    }



    // Fetch properties by filters
    @GetMapping("/filter")
    public ResponseEntity<List<Property>> filterProperties(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String propertyType
    ) {
        return ResponseEntity.ok(propertyService.filterProperties(minPrice, maxPrice, location, propertyType));
    }

    // Update a property
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(
            @PathVariable Long id,
            @RequestBody Property updatedProperty
    ) {
        return ResponseEntity.ok(propertyService.updateProperty(id, updatedProperty));
    }

    // Delete a property
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property deleted successfully.");
    }
    @GetMapping("/landlord/{landlordId}")
    public ResponseEntity<List<Property>> getPropertiesByLandlord(@PathVariable String landlordId) {
        List<Property> properties = propertyService.getPropertiesByLandlordId(landlordId);
        if (properties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(properties);
    }
    // Endpoint to get total properties
    @GetMapping("/total")
    public long getTotalProperties() {
        return propertyService.getTotalProperties();
    }
}

