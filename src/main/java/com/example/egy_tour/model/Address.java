package com.example.egy_tour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Address parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Address> children;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<TourismSpot> tourismSpots;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlots;
}
