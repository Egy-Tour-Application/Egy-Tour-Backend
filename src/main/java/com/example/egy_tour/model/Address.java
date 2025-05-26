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
    private Address parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Address> children;

    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<TourismSpot> tourismSpots;

    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlots;

    public String getFullAddressName() {
        StringBuilder addressBuilder = new StringBuilder(name);
        Address current = parent;
        while (current != null) {
            addressBuilder.append(", ").append(current.getName());
            current = current.getParent();
        }
        return addressBuilder.toString();
    }

    public Address(String name) {
        this.name = name;
    }

}
