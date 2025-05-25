package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.service.TourismSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<Map<String, Object>> getTourismSpotsForMap() {
        List<TourismSpot> spots = tourismSpotService.getAllTourismSpots();
        return spots.stream()
                .map(spot -> {
                    Map<String, Object> place = new HashMap<>();
                    place.put("name", spot.getTitle());
                    place.put("position", Map.of(
                            "latitude", spot.getLatitude(),
                            "longitude", spot.getLongitude()
                    ));
                    place.put("description", spot.getDescription());
                    place.put("imageUrl", spot.getImage());
                    place.put("egyptianPrice", spot.getEgyptianPrice());
                    place.put("foreignerPrice", spot.getForeignerPrice());
                    place.put("openingTime", spot.getOpeningTime());
                    place.put("closingTime", spot.getClosingTime());
                    return place;
                })
                .collect(Collectors.toList());
    }

}
