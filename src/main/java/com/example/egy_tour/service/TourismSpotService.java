package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.repository.TourismSpotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourismSpotService {
    private final TourismSpotRepository tourismSpotRepository;
    private final AddressService addressService;
    private final ModelMapper mapper;


    public TourismSpotService(TourismSpotRepository tourismSpotRepository, AddressService addressService, ModelMapper mapper) {
        this.tourismSpotRepository = tourismSpotRepository;
        this.addressService = addressService;
        this.mapper = mapper;
    }

    public TourismSpot createTourismSpot(CreateTourismSpotDTO createTourismSpotDTO) {
        Address tourismSpotAddress = addressService.getAddressByName(createTourismSpotDTO.getAddress());
        if (tourismSpotAddress == null) {
            throw new RuntimeException("Address not found");
        }
        TourismSpot tourismSpot = mapper.map(createTourismSpotDTO, TourismSpot.class);
        tourismSpot.setAddress(tourismSpotAddress);
        return tourismSpotRepository.save(tourismSpot);
    }

    public List<TourismSpot> getAllTourismSpots() {
        return tourismSpotRepository.findAll();
    }
}
