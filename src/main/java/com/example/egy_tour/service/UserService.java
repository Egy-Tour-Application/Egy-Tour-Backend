package com.example.egy_tour.service;

import com.example.egy_tour.dto.AddUserPreferenceDTO;
import com.example.egy_tour.dto.AddUserTourismSpotDTO;
import com.example.egy_tour.dto.UpdateUserDTO;
import com.example.egy_tour.model.Preference;
import com.example.egy_tour.model.TourismSpot;
import com.example.egy_tour.model.User;
import com.example.egy_tour.repository.PreferenceRepository;
import com.example.egy_tour.repository.TourismSpotRepository;
import com.example.egy_tour.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PreferenceRepository preferenceRepository;
    private final TourismSpotRepository tourismSpotRepository;

    @Autowired
    public UserService(UserRepository userRepository, PreferenceRepository preferenceRepository,
                       TourismSpotRepository tourismSpotRepository) {
        this.userRepository = userRepository;
        this.preferenceRepository = preferenceRepository;
        this.tourismSpotRepository = tourismSpotRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(updateUserDTO.getFirstName());
            user.setLastName(updateUserDTO.getLastName());
            user.setPhone(updateUserDTO.getPhone());
            return userRepository.save(user);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean addUserPreference(AddUserPreferenceDTO addUserPreferenceDTO) {
        User user = userRepository.findById(addUserPreferenceDTO.getUserId()).orElse(null);
        if (user == null) {
            return false;
        }

        Preference preference = preferenceRepository.findByName(addUserPreferenceDTO.getPreferenceName()).orElse(null);
        if (preference == null) {
            preference = preferenceRepository.save(new Preference(addUserPreferenceDTO.getPreferenceName()));
        }

        if (!user.getPreferences().contains(preference)) {
            user.getPreferences().add(preference);
            userRepository.save(user);
        }
        return true;
    }

    public Boolean addUserTourismSpot(AddUserTourismSpotDTO addUserTourismSpotDTO) {
        User user = userRepository.findById(addUserTourismSpotDTO.getUserId()).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        TourismSpot tourismSpot = tourismSpotRepository.findById(addUserTourismSpotDTO.getTourismSpotId()).orElse(null);
        if (tourismSpot == null) {
            throw new RuntimeException("Tourism spot not found");
        }
        if (!user.getTourismSpots().contains(tourismSpot)) {
            user.getTourismSpots().add(tourismSpot);
            userRepository.save(user);
        }
        return true;
    }

}
