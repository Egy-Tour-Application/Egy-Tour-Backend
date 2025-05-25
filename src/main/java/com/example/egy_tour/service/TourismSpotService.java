package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.repository.TourismSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourismSpotService {
    private final TourismSpotRepository tourismSpotRepository;
    private final AddressService addressService;

    public TourismSpotService(TourismSpotRepository tourismSpotRepository, AddressService addressService) {
        this.tourismSpotRepository = tourismSpotRepository;
        this.addressService = addressService;
    }

    public TourismSpot createTourismSpot(CreateTourismSpotDTO createTourismSpotDTO) {
        Address tourismSpotAddress = addressService.getAddressById(createTourismSpotDTO.getAddressId());
        if (tourismSpotAddress == null) {
            throw new RuntimeException("Address not found");
        }
        TourismSpot tourismSpot = new TourismSpot(
                createTourismSpotDTO.getTitle(),
                createTourismSpotDTO.getDescription(),
                createTourismSpotDTO.getType(),
                createTourismSpotDTO.getEgyptian_price(),
                createTourismSpotDTO.getForeigner_price(),
                tourismSpotAddress,
                createTourismSpotDTO.getOpening_time(),
                createTourismSpotDTO.getClosing_time(),
                createTourismSpotDTO.getImage()
        );
        return tourismSpotRepository.save(tourismSpot);
    }

    public List<TourismSpot> getAllTourismSpots() {
        return tourismSpotRepository.findAll();
    }
}
