package com.fabien.reservation.repository;

import com.fabien.reservation.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);
    List<Booking> findByEquipmentId(Long equipmentId);

    @Query("SELECT b FROM Booking b WHERE b.equipmentId = :equipmentId " +
            "AND b.startDateTime < :endDateTime " +
            "AND b.endDateTime > :startDateTime")
    List<Booking> findOverlappingBookings(
            @Param("equipmentId") Long equipmentId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );

}
