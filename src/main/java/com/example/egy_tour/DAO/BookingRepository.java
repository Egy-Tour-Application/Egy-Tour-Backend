package com.example.egy_tour.DAO;

import com.example.egy_tour.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
