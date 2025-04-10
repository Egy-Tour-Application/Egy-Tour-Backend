package com.example.egy_tour.model.entity;

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
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opening_time")
    private Date openingTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opening_time")
    private Date closingTime;

    @Column(name = "location_link")
    private String locationLink;

    @OneToMany(mappedBy = "tourismSpot")
    private List<TourismSpotReview> reviews;
}
