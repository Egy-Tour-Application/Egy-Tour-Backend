package com.example.egy_tour.DAO;

import com.example.egy_tour.model.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> {
}
