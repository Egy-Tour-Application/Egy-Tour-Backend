package com.example.egy_tour.controller;

import com.example.egy_tour.dto.*;
import com.example.egy_tour.model.TimeSlot;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.model.User;
import com.example.egy_tour.service.TimeSlotService;
import com.example.egy_tour.service.TourGuideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private TourGuideService tourGuideService;

    @PostMapping("/tour-guide/{id}")
    public ResponseEntity<TimeSlot> createTimeSlot(
            @PathVariable Long id,
            @Valid @RequestBody CreateTimeSlotDTO createTimeSlotDTO) {
        TimeSlot timeSlot = timeSlotService.createTimeSlot(id, createTimeSlotDTO);
        return ResponseEntity.ok(timeSlot);
    }

    @GetMapping("/tour-guide/{id}")
    public ResponseEntity<List<TimeSlot>> getTimeSlotsByTourGuide(@PathVariable Long id) {
        List<TimeSlot> timeSlots = timeSlotService.getTimeSlotsByTourGuide(id);
        return ResponseEntity.ok(timeSlots);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSlot> updateTimeSlot(
            @PathVariable Long id,
            @Valid @RequestBody CreateTimeSlotDTO updateTimeSlotDTO) {
        TimeSlot timeSlot = timeSlotService.updateTimeSlot(id, updateTimeSlotDTO);
        return ResponseEntity.ok(timeSlot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TourGuideResponseDTO>> findAvailableTourGuides(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime  endDate,
            @RequestParam Long addressId
    ) {
        List<TourGuide> tourGuides = timeSlotService.findAvailableTourGuides(
                new SearchTimeSlotsDTO(startDate, endDate, addressId)
        );

        List<TourGuideResponseDTO> dtoList = tourGuides.stream()
                .map(tourGuideService::toTourGuideResponseDTO)
                .toList();

        return ResponseEntity.ok(dtoList);
    }


}