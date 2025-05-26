package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateTourGuideDTO;
import com.example.egy_tour.dto.TimeSlotResponseDTO;
import com.example.egy_tour.dto.TourGuideResponseDTO;
import com.example.egy_tour.dto.UserResponseDTO;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.model.User;
import com.example.egy_tour.repository.TourGuideRepository;
import com.example.egy_tour.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TourGuideService {

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TourGuide createTourGuide(CreateTourGuideDTO createTourGuideDTO) {
        User user = userRepository.findById(createTourGuideDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Check if user is already a tour guide
        if (user.getTourGuide() != null) {
            throw new IllegalStateException("User is already a tour guide");
        }

        TourGuide tourGuide = new TourGuide();
        tourGuide.setUser(user);
        tourGuide.setPrice(createTourGuideDTO.getPrice());

        return tourGuideRepository.save(tourGuide);
    }

    public TourGuide getTourGuide(Long tourGuideId) {
        return tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new EntityNotFoundException("Tour guide not found"));
    }

    @Transactional
    public void deleteTourGuide(Long tourGuideId) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new EntityNotFoundException("Tour guide not found"));
        tourGuideRepository.delete(tourGuide);
    }

    @Transactional
    public TourGuide updateTourGuidePrice(Long tourGuideId, Double price) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new EntityNotFoundException("Tour guide not found"));

        tourGuide.setPrice(price);

        return tourGuideRepository.save(tourGuide);
    }


    public TourGuideResponseDTO toTourGuideResponseDTO(TourGuide tourGuide) {
        User user = tourGuide.getUser();
        UserResponseDTO userDTO = new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getNationality(),
                user.getGender()
        );

        List<TimeSlotResponseDTO> timeSlotsDTO = tourGuide.getTimeSlots().stream()
                .map(ts -> new TimeSlotResponseDTO(ts.getId(), ts.getStartTime(), ts.getEndTime()))
                .toList();

        return new TourGuideResponseDTO(tourGuide.getId(),tourGuide.getPrice(), userDTO, timeSlotsDTO);
    }

}
