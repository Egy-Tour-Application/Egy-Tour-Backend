package com.example.egy_tour.repository;

import com.example.egy_tour.model.TimeSlot;
import com.example.egy_tour.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByTourGuide(TourGuide tourGuide);
    List<TimeSlot> findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime startTime, LocalDateTime  endTime);
}
