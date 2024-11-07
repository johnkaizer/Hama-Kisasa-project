package com.project.hamakisasa.booking;

import com.project.hamakisasa.properties.Property;
import com.project.hamakisasa.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant; // Reference to User as a tenant

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; // Reference to Property

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // Pending, Confirmed, Cancelled

}
