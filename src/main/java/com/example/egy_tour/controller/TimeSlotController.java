package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateTimeSlotDTO;
import com.example.egy_tour.dto.SearchTimeSlotsDTO;
import com.example.egy_tour.model.TimeSlot;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.service.TimeSlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

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
    public ResponseEntity<List<TourGuide>> findAvailableTourGuides(@Valid @RequestBody SearchTimeSlotsDTO searchDTO) {
        List<TourGuide> availableTourGuides = timeSlotService.findAvailableTourGuides(searchDTO);
        return ResponseEntity.ok(availableTourGuides);
    }
}