package com.project.hamakisasa.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Add a new booking
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Edit an existing booking
    public Booking editBooking(Long id, Booking updatedBooking) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
            booking.setTenantId(updatedBooking.getTenantId());
            booking.setPropertyId(updatedBooking.getPropertyId());
            booking.setLandlordId(updatedBooking.getLandlordId());
            booking.setPropertyName(updatedBooking.getPropertyName());
            booking.setAppointmentDate(updatedBooking.getAppointmentDate());
            booking.setName(updatedBooking.getName());
            booking.setPhoneNumber(updatedBooking.getPhoneNumber());
            booking.setStatus(updatedBooking.getStatus());
            return bookingRepository.save(booking);
        }
        return null; // Or throw a custom exception
    }

    // Delete a booking
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    // Fetch all bookings (for Admin)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Fetch bookings by tenantId (for the logged-in user)
    public List<Booking> getBookingsByTenantId(Long tenantId) {
        return bookingRepository.findByTenantId(tenantId);
    }

    // Fetch bookings for a specific landlord by landlordId
    public List<Booking> getBookingsByLandlordId(Long landlordId) {
        return bookingRepository.findByLandlordId(landlordId);
    }

    public void cancelBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus("Cancelled");
            bookingRepository.save(booking);
        } else {
            // Log a message or handle gracefully if needed
            System.out.println("Booking not found with id: " + id);
        }
    }


    public void updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id " + id));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }
    public long getTotalBookings() {
        return bookingRepository.count();
    }

}
