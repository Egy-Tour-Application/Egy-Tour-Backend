package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateTourGuideDTO;
import com.example.egy_tour.model.TourGuide;
import com.example.egy_tour.model.User;
import com.example.egy_tour.repository.TourGuideRepository;
import com.example.egy_tour.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
