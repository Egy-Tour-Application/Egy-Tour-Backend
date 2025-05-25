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
    private double foreigner_price;
    private double egyptian_price;
    private String image;
    private String opening_time;
    private String closing_time;
    private double latitude;
    private double longitude;
    private String address;
    private Long addressId;

}
