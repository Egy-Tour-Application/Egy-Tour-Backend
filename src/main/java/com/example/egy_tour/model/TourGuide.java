package com.example.egy_tour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tour_guides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourGuide {
    @Id
    private String userId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId
    private User user;

    @OneToMany(mappedBy = "tourGuide", cascade = CascadeType.ALL)
    private List<TourGuideReview> tourGuideReviews;

    @OneToMany(mappedBy = "tourGuide", cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlots;

    @OneToMany(mappedBy = "tourGuide", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
