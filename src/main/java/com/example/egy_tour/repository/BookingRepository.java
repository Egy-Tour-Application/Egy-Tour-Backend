package com.example.egy_tour.repository;

import com.example.egy_tour.model.Booking;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
//    Booking findBookingForTourGuide ( User user,
//                                      TourGuide tourGuide,
//                                      LocalDateTime endTime,
//                                      LocalDateTime startTime);

}
