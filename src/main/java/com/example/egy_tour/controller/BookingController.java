package com.example.egy_tour.controller;

import com.example.egy_tour.dto.BookingRequestDTO;
import com.example.egy_tour.dto.BookingResponseDTO;
import com.example.egy_tour.dto.CreateBookingDTO;
import com.example.egy_tour.dto.UpdateBookingDTO;
import com.example.egy_tour.model.Booking;
import com.example.egy_tour.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<List<BookingResponseDTO>> getBookingById(@PathVariable Long id) {
        List<Booking> booking = bookingService.getBookingByUser(id);
        List<BookingResponseDTO> bookingDTOs = booking.stream()
                .map(bookingService::mapToBookingResponse)
                .toList();

        return ResponseEntity.ok(bookingDTOs);
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

    @GetMapping("/check")
    public ResponseEntity<BookingResponseDTO> checkBooking(
            @RequestParam Long userId,
            @RequestParam Long tourGuideId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {

        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO(userId, tourGuideId, startTime, endTime);
        BookingResponseDTO bookingResponseDTO = bookingService.checkForBooking(bookingRequestDTO);

        if (bookingResponseDTO == null) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(bookingResponseDTO);
    }


}
