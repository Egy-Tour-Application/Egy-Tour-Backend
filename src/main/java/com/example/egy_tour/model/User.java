package com.example.egy_tour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "user")
    private List<Program> programs;

    @OneToMany(mappedBy = "user")
    private List<TourGuideReview> tourGuideReviews;

    @OneToMany(mappedBy = "user")
    private List<TourismSpotReview> tourismSpotReviews;

    @OneToMany(mappedBy = "user")
    private List<ChatbotHistory> chatbotHistory;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(
            name = "user_languages",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "language_id", nullable = false)
    )
    private List<Language> languages;

    @ManyToMany
    @JoinTable(
            name = "user_preferences",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_preference_id", nullable = false)
    )
    private List<UserPreference> userPreferences;

    @OneToOne (mappedBy = "user")
    private TourGuide tourGuide;
}