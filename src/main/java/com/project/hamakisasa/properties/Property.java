package com.project.hamakisasa.properties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Base64;

@Entity
@Table(name = "properties")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private BigDecimal price;

    private String propertyType; // Apartment, Bungalow, One Bedroom, Bedsitter, Two Bedroom,Studio Apartment.

    private String description;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;

    private boolean status;

    private String landlordId; // Relationship with User (landlord)
}
