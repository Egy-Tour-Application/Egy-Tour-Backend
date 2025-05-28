package com.example.egy_tour.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTourismSpotDTO {

    private String title;
    private String description;
    private String type;
    private double foreignerPrice;
    private double egyptianPrice;
    private String imageUrl;
    private String openingTime;
    private String closingTime;
    private double latitude;
    private double longitude;
    private String address;
}
