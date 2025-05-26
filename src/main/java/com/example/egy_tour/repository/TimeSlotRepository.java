package com.example.egy_tour.repository;

import com.example.egy_tour.model.TimeSlot;
import com.example.egy_tour.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByTourGuide(TourGuide tourGuide);

    @Query("SELECT ts FROM TimeSlot ts WHERE ts.startTime <= :endTime AND ts.endTime >= :startTime")
    List<TimeSlot> findAvailableTimeSlots(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime  endTime);
}
