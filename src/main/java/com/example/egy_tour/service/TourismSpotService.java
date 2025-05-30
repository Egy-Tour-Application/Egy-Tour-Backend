package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateAddressDTO;
import com.example.egy_tour.dto.UserLikedTourismSpotDTO;
import com.example.egy_tour.dto.CreateTourismSpotDTO;
import com.example.egy_tour.dto.TourismSpotResponse;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.model.User;
import com.example.egy_tour.repository.TourismSpotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourismSpotService {
    private final TourismSpotRepository tourismSpotRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final ImageService imageService;
    private final ModelMapper mapper;


    public TourismSpotService(TourismSpotRepository tourismSpotRepository, AddressService addressService,
                              ImageService imageService, UserService userService, ModelMapper mapper) {
        this.tourismSpotRepository = tourismSpotRepository;
        this.addressService = addressService;
        this.imageService = imageService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public TourismSpot createTourismSpot(CreateTourismSpotDTO createTourismSpotDTO) {
        try {
            Address tourismSpotAddress = addressService.getAddressByName(createTourismSpotDTO.getAddress());
            if (tourismSpotAddress == null) {
                tourismSpotAddress = addressService.createAddress(
                        new CreateAddressDTO(createTourismSpotDTO.getAddress())
                );
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

    public Boolean handleUserLikedTourismSpot(UserLikedTourismSpotDTO addUserTourismSpotDTO, boolean addLike) {
        User user = userService.getUserById(addUserTourismSpotDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        TourismSpot tourismSpot = tourismSpotRepository.findById(addUserTourismSpotDTO.getTourismSpotId()).orElse(null);
        if (tourismSpot == null) {
            throw new RuntimeException("Tourism spot not found");
        }
        if (addLike) {
            if (!user.getTourismSpots().contains(tourismSpot)) {
                user.getTourismSpots().add(tourismSpot);
                userService.saveUserLikedSpots(user);
            }
        } else {
            if (user.getTourismSpots().contains(tourismSpot)) {
                user.getTourismSpots().remove(tourismSpot);
                userService.saveUserLikedSpots(user);
            }
        }
        return true;
    }

}
