package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.dto.TourismSpotResponse;
import com.example.egy_tour.dto.UserResponseDTO;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.repository.TourismSpotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourismSpotService {
    private final TourismSpotRepository tourismSpotRepository;
    private final AddressService addressService;
    private final ImageService imageService;
    private final ModelMapper mapper;


    public TourismSpotService(TourismSpotRepository tourismSpotRepository, AddressService addressService, ModelMapper mapper, ImageService imageService) {
        this.tourismSpotRepository = tourismSpotRepository;
        this.addressService = addressService;
        this.mapper = mapper;
        this.imageService = imageService;
    }

    public TourismSpot createTourismSpot(CreateTourismSpotDTO createTourismSpotDTO) {
        try {
            Address tourismSpotAddress = addressService.getAddressByName(createTourismSpotDTO.getAddress());
            if (tourismSpotAddress == null) {
                throw new RuntimeException("Address not found");
            }
            byte[] image = imageService.getImageFromUrl(createTourismSpotDTO.getImageUrl());
            TourismSpot tourismSpot = mapper.map(createTourismSpotDTO, TourismSpot.class);
            tourismSpot.setImage(image);
            tourismSpot.setAddress(tourismSpotAddress);
            return tourismSpotRepository.save(tourismSpot);
        } catch (Exception e) {
            throw new RuntimeException("Error creating tourism spot");
        }
    }

    public List<TourismSpotResponse> getAllTourismSpotsWithLikes(Long userId) {
        List<TourismSpotResponse> tourismSpotResponses = new ArrayList<>();
        List<TourismSpot> tourismSpots = tourismSpotRepository.findAll();
        for (TourismSpot tourismSpot : tourismSpots) {
            TourismSpotResponse tourismSpotResponse = mapper.map(tourismSpot, TourismSpotResponse.class);
            int userIsLikedSpotsCount = tourismSpotRepository.userIsLikedSpot(userId, tourismSpot.getId());
            tourismSpotResponse.setLiked(userIsLikedSpotsCount > 0);
            tourismSpotResponse.setNumLikes(tourismSpotRepository.countTourismSpotsNumLikes(tourismSpot.getId()));
            tourismSpotResponses.add(tourismSpotResponse);
        }
        return tourismSpotResponses;
    }

    public List<TourismSpot> getAllTourismSpots() {
        return tourismSpotRepository.findAll();
    }

}
