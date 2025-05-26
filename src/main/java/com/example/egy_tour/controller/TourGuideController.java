package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateTourGuideDTO;
import com.example.egy_tour.dto.TourGuideResponseDTO;
import com.example.egy_tour.dto.UpdateTourGuideDTO;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.service.TourGuideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tour-guides")
public class TourGuideController {

    @Autowired
    private TourGuideService tourGuideService;

    @PostMapping
    public ResponseEntity<TourGuide> createTourGuide(@Valid @RequestBody CreateTourGuideDTO createTourGuideDTO) {
        TourGuide tourGuide = tourGuideService.createTourGuide(createTourGuideDTO);
        return ResponseEntity.ok(tourGuide);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourGuide> getTourGuide(@PathVariable Long id) {
        TourGuide tourGuide = tourGuideService.getTourGuide(id);
        return ResponseEntity.ok(tourGuide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourGuide(@PathVariable Long id) {
        tourGuideService.deleteTourGuide(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourGuideResponseDTO> updatePrice(
            @PathVariable Long id,
            @RequestBody UpdateTourGuideDTO updatePriceDTO) {
        TourGuide updatedTourGuide = tourGuideService.updateTourGuidePrice(id, updatePriceDTO.getPrice());
        TourGuideResponseDTO responseDTO = tourGuideService.toTourGuideResponseDTO(updatedTourGuide);

        return ResponseEntity.ok(responseDTO);

    }
}