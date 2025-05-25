package com.example.egy_tour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Address parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Address> children;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<TourismSpot> tourismSpots;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlots;

    public Address(String name) {
        this.name = name;
    }

}
