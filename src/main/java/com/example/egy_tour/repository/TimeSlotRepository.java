package com.example.egy_tour.repository;

import com.example.egy_tour.model.TimeSlot;
import com.example.egy_tour.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByTourGuide(TourGuide tourGuide);

    @Query(nativeQuery = true, value = "select * FROM time_slots where start_time >= ?1 AND end_time <= ?2")
    List<TimeSlot> findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime start, LocalDateTime  end);
}
