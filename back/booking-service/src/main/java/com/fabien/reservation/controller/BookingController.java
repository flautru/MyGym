package com.fabien.reservation.controller;

import com.fabien.reservation.model.Booking;
import com.fabien.reservation.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Booking> getAllBooking(){
        return bookingService.getAllBooking();
    }

    @GetMapping("users/{id}")
    public List<Booking> getBookingByUserId(@PathVariable Long id) {
        return bookingService.getBookingByUserId(id);
    }

    @GetMapping("equipment/{id}")
    public List<Booking> getBookingByEquipmentId(@PathVariable Long id) {
        return bookingService.getBookingByEquipment(id);
    }

    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking){
        return bookingService.saveBooking(booking);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }
}
