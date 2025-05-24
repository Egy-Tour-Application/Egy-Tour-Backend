package com.example.egy_tour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tourism_spot_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourismSpotReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tourism_spot_id", nullable = false)
    private TourismSpot tourismSpot;
}