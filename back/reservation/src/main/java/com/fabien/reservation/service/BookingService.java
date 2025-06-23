package com.fabien.reservation.service;

import com.fabien.reservation.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<Booking> getBookingById(Long id);
    List<Booking> getAllBooking();
    List<Booking> getBookingByUserId(Long id);
    List<Booking> getBookingByEquipment(Long id);
    Booking saveBooking(Booking booking);
    void deleteBookingById(Long id);
}
