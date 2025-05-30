package com.example.egy_tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "description", length = 4000)
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

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "image" , columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "tourismSpot", cascade = CascadeType.ALL)
    private List<TourismSpotReview> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "tourismSpot", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
