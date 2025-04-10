package com.example.egy_tour.model.entity;

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

    @ManyToMany
    @JoinTable(
            name = "user_languages",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    @OneToMany(mappedBy = "user")
    private List<Program> programs;

    @OneToMany(mappedBy = "user")
    private List<TourGuideReview> tourGuideReviews;

    @OneToMany(mappedBy = "user")
    private List<TourismSpotReview> tourismSpotReviews;

    @ManyToMany(mappedBy = "user")
    @JoinTable(
            name = "user_preferences",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_preference_id")
    )
    private List<UserPreference> userPreferences;
}