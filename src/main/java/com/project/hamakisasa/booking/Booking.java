package com.project.hamakisasa.booking;

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
    private Long tenantId;
    private Long propertyId;
    private Long landlordId;
    private String propertyName;
    private LocalDateTime appointmentDate;
    private String name;
    private String phoneNumber;
    private String status; // Pending, Confirmed, Cancelled

}
