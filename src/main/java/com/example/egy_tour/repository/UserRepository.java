package com.example.egy_tour.repository;

import com.example.egy_tour.model.Preference;
import com.example.egy_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "select  p.id, p.name FROM preferences p INNER JOIN user_preferences up on p.id = up.preference_id where up.user_id = ?1")
    List<Preference> findUserPreferences (Long userId);
}
