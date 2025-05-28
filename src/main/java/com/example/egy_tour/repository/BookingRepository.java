package com.example.egy_tour.repository;

import com.example.egy_tour.model.Booking;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    Booking findByUserAndTourGuideAndStartTimeAndEndTime(User user,
                                                                   TourGuide tourGuide,
                                                                   LocalDateTime startTime, LocalDateTime endTime);

}
