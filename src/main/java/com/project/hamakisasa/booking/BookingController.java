package com.project.hamakisasa.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Add a new booking
    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    // Edit an existing booking
    @PutMapping("/{id}")
    public Booking editBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.editBooking(id, booking);
    }

    // Delete a booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    // Fetch all bookings (Admin)
    @GetMapping("/admin")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Fetch bookings for a specific tenant (logged-in user)
    @GetMapping("/user/{tenantId}")
    public List<Booking> getBookingsByTenantId(@PathVariable Long tenantId) {
        return bookingService.getBookingsByTenantId(tenantId);
    }

    // Fetch bookings for a specific landlord (given landlordId)
    @GetMapping("/landlord/bookings")
    public List<Booking> getBookingsByLandlord(@RequestParam Long landlordId) {
        return bookingService.getBookingsByLandlordId(landlordId);
    }

    // Cancel a booking
    @PostMapping("/{id}/cancel")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

    // Update booking status
    @PutMapping("/update-status/{id}")
    public void updateBookingStatus(@PathVariable Long id, @RequestBody BookingStatusRequest request) {
        bookingService.updateBookingStatus(id, request.getStatus());
    }

    // Endpoint to get total bookings
    @GetMapping("/total")
    public long getTotalBookings() {
        return bookingService.getTotalBookings();
    }
}
