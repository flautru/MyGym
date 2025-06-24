package com.fabien.reservation.service.impl;

import com.fabien.reservation.model.Booking;
import com.fabien.reservation.repository.BookingRepository;
import com.fabien.reservation.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingByUserId(Long id) {
        return bookingRepository.findByUserId(id);
    }

    public List<Booking> getBookingByEquipment(Long id) {
        return bookingRepository.findByEquipmentId(id);
    }

    public Booking saveBooking(Booking booking) {
        if(isEquipmentAvailable(booking.getEquipmentId(), booking.getStartDateTime(), booking.getEndDateTime())){
            return bookingRepository.save(booking);
        }
        else {
            return new Booking();
        }

    }

    public boolean isEquipmentAvailable(Long equipmentId, LocalDateTime start, LocalDateTime end) {
        List<Booking> overlapping = bookingRepository.findOverlappingBookings(equipmentId, start, end);
        return overlapping.isEmpty();
    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }
}
