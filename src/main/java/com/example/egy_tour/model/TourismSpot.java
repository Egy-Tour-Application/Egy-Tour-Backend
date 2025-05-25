package com.example.egy_tour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tourism_spots")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TourismSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "egyptian_price")
    private double egyptianPrice;

    @Column(name = "foreigner_price")
    private double foreignerPrice;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opening_time")
    private Date openingTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closing_time")
    private Date closingTime;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "tourismSpot", cascade = CascadeType.ALL)
    private List<TourismSpotReview> reviews;

    @OneToMany(mappedBy = "tourismSpot", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
