package com.project.hamakisasa.properties;

import com.project.hamakisasa.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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

    private int bedrooms;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType; // Apartment, Bungalow, etc.

    private String description;

    @ElementCollection
    private List<String> photos; // List of URLs for property photos

    private boolean available;

    private boolean verified; // Property verified status

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord; // Relationship with User (landlord)

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
