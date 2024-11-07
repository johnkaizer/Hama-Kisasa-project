package com.project.hamakisasa.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setAppointmentDate(bookingDetails.getAppointmentDate());
        booking.setStatus(bookingDetails.getStatus());
        booking.setTenant(bookingDetails.getTenant());
        booking.setProperty(bookingDetails.getProperty());

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByTenant(Long tenantId) {
        return bookingRepository.findByTenant_Id(tenantId);
    }

    public List<Booking> getBookingsByProperty(Long propertyId) {
        return bookingRepository.findByProperty_Id(propertyId);
    }
}
