package com.example.egy_tour.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourismSpotResponseDTO {
    private Long id;
    private String title;
    private String description;
}
