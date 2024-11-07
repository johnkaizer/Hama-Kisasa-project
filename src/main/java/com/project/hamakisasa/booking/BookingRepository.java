package com.project.hamakisasa.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByTenant_Id(Long tenantId);

    List<Booking> findByProperty_Id(Long propertyId);
}

