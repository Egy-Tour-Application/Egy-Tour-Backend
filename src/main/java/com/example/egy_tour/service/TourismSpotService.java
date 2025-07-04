package com.example.egy_tour.service;

import com.example.egy_tour.dto.*;
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
    private final ChatbotService chatbotService;
    private final UserService userService;
    private final AddressService addressService;
    private final ImageService imageService;
    private final ModelMapper mapper;


    public TourismSpotService(TourismSpotRepository tourismSpotRepository, ChatbotService chatbotService, AddressService addressService,
                              ImageService imageService, UserService userService, ModelMapper mapper) {
        this.tourismSpotRepository = tourismSpotRepository;
        this.chatbotService = chatbotService;
        this.addressService = addressService;
        this.imageService = imageService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public TourismSpot createTourismSpot(CreateTourismSpotDTO createTourismSpotDTO) {
        try {
            Address tourismSpotAddress;
            String[] address = createTourismSpotDTO.getAddress().split(",");
            if (address.length > 1 && !address[0].equals(address[1])) {
                tourismSpotAddress = addressService.createAddress(new CreateAddressDTO(address[0], address[1]));
            } else {
                tourismSpotAddress = addressService.getAddressByName(address[0]);
                if (tourismSpotAddress == null) {
                    tourismSpotAddress = addressService.createAddress(new CreateAddressDTO(address[0]));
                }
            }
            byte[] image = imageService.getImageFromUrl(createTourismSpotDTO.getImageUrl());
            TourismSpot tourismSpot = mapper.map(createTourismSpotDTO, TourismSpot.class);
            tourismSpot.setImage(image);
            tourismSpot.setAddress(tourismSpotAddress);
            TourismSpot savedSpot = tourismSpotRepository.save(tourismSpot);
            CreateVectorDTO vectorDTO = new CreateVectorDTO(
                    savedSpot.getId().toString(),
                    "tourism_spot",
                    savedSpot.toString()
            );
            chatbotService.createVector(vectorDTO);
            return savedSpot;


        } catch (Exception e) {
            throw new RuntimeException("Error creating tourism spot");
        }
    }


    public List<TourismSpotResponse> getAllTourismSpotsWithLikes(Long userId) {
        List<TourismSpotResponse> tourismSpotResponses = new ArrayList<>();
        List<TourismSpot> tourismSpots = tourismSpotRepository.findAll();
        for (TourismSpot tourismSpot : tourismSpots) {
            TourismSpotResponse tourismSpotResponse = mapper.map(tourismSpot, TourismSpotResponse.class);
            tourismSpotResponse.setLiked(tourismSpotRepository.userIsLikedSpot(userId, tourismSpot.getId()));
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
