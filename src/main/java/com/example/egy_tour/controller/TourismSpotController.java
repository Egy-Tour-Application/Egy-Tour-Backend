package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.dto.TourismSpotPinDTO;
import com.example.egy_tour.dto.TourismSpotResponse;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.repository.AddressRepository;
import com.example.egy_tour.service.AddressService;
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
        return ResponseEntity.ok(tourismSpotService.createTourismSpot(createTourismSpotDTO));
    }

    @GetMapping
    public List<TourismSpotResponse> getAllTourismSpots() {
        List<TourismSpot> tourismSpot = tourismSpotService.getAllTourismSpots();
        return tourismSpot.stream()
                .map(TourismSpotResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/map-locations")
    public List<TourismSpotPinDTO> getTourismSpotsForMap() {
        List<TourismSpot> spots = tourismSpotService.getAllTourismSpots();
        return spots.stream()
                .map(TourismSpotPinDTO::new)
                .collect(Collectors.toList());
    }
}
