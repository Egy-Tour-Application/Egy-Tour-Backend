package com.example.egy_tour.controller;

import com.example.egy_tour.dto.UserLikedTourismSpotDTO;
import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.dto.TourismSpotPinDTO;
import com.example.egy_tour.dto.TourismSpotResponse;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.repository.TourismSpotRepository;
import com.example.egy_tour.service.TourismSpotService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tourism-spot")
public class TourismSpotController {

    @Autowired
    private final TourismSpotService tourismSpotService;
    @Autowired
    private TourismSpotRepository tourismSpotRepository;

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

    @GetMapping("/user/{id}")
    public List<TourismSpotResponse> getAllTourismSpots(@PathVariable long id) {
        return tourismSpotService.getAllTourismSpotsWithLikes(id);
    }

    @GetMapping("/map-locations")
    public List<TourismSpotPinDTO> getTourismSpotsForMap() {
        List<TourismSpot> spots = tourismSpotService.getAllTourismSpots();
        return spots.stream()
                .map(TourismSpotPinDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/like-spot")
    public ResponseEntity<Boolean> addUserLikedTourismSpot(@Valid @RequestBody UserLikedTourismSpotDTO addUserTourismSpotDTO) {
        return ResponseEntity.ok(tourismSpotService.handleUserLikedTourismSpot(addUserTourismSpotDTO , true));
    }

    @PostMapping("/unlike-spot")
    public ResponseEntity<Boolean> removeUserLikedTourismSpot(@Valid @RequestBody UserLikedTourismSpotDTO addUserTourismSpotDTO) {
        return ResponseEntity.ok(tourismSpotService.handleUserLikedTourismSpot(addUserTourismSpotDTO , false));
    }

    @GetMapping("/tourism-spot/{id}/name")
    public ResponseEntity<String> getTourismSpotName(@PathVariable Long id) {
        TourismSpot spot = tourismSpotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tourism spot not found"));
        return ResponseEntity.ok(spot.getTitle());
    }

}
