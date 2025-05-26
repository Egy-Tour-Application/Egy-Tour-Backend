package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.dto.TourismSpotPinDTO;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.service.TourismSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tourism-spot")
public class TourismSpotController {

    private final TourismSpotService tourismSpotService;

    public TourismSpotController(TourismSpotService tourismSpotService) {
        this.tourismSpotService = tourismSpotService;
    }

    @PostMapping
    public ResponseEntity<TourismSpot> createTourismSpot(@RequestBody CreateTourismSpotDTO createTourismSpotDTO) {
        try {
            return ResponseEntity.ok(tourismSpotService.createTourismSpot(createTourismSpotDTO));
        } catch (Exception e) {
            return new ResponseEntity("Invalid request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<TourismSpot> getAllTourismSpots() {
        return tourismSpotService.getAllTourismSpots();
    }

    @GetMapping("/map-locations")
    public List<TourismSpotPinDTO> getTourismSpotsForMap() {
        List<TourismSpot> spots = tourismSpotService.getAllTourismSpots();
        return spots.stream()
                .map(TourismSpotPinDTO::new)
                .collect(Collectors.toList());
    }
}
