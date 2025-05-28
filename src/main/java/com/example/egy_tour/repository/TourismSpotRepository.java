package com.example.egy_tour.repository;

import com.example.egy_tour.model.TourismSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourismSpotRepository extends JpaRepository<TourismSpot, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM user_liked_spots WHERE user_id = ?1 AND tourism_spot_id = ?2")
    int userIsLikedSpot(Long userId, Long spotId);

    @Query(nativeQuery = true, value = "select count(user_liked_spots.tourism_spot_id)  FROM user_liked_spots where  tourism_spot_id = ?1")
    int countTourismSpotsNumLikes(Long spotId);

}
