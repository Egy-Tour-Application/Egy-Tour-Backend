package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateBookingDTO;
import com.example.egy_tour.dto.UpdateBookingDTO;
import com.example.egy_tour.model.Booking;
import com.example.egy_tour.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingDTO bookingDTO) {
        Booking booking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Booking>> getBookingById(@PathVariable Long id) {
        List<Booking> booking = bookingService.getBookingByUser(id);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @RequestBody UpdateBookingDTO bookingDTO) {

        Booking booking = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(booking);
    }

}
