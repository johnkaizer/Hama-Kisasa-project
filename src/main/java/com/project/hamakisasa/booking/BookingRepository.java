package com.project.hamakisasa.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Fetch all bookings (for Admin)
    List<Booking> findAll();

    // Fetch bookings by tenantId, used by the logged-in user
    List<Booking> findByTenantId(Long tenantId);

    // Optional fetch by id (for edit functionality)
    Optional<Booking> findById(Long id);

    // Find bookings by landlordId
    List<Booking> findByLandlordId(Long landlordId);
}
