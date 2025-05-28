package com.example.egy_tour.dto;

import com.example.egy_tour.model.TourismSpot;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourismSpotResponse {
    private Long id;
    private String title;
    private String description;
    private String type;
    private double foreignerPrice;
    private double egyptianPrice;
    private String openingTime;
    private String closingTime;
    private byte[] image;
    private String address;
    private boolean liked;
    private int numLikes;

    public TourismSpotResponse(TourismSpot tourismSpot) {
        this.id = tourismSpot.getId();
        this.title = tourismSpot.getTitle();
        this.description = tourismSpot.getDescription();
        this.type = tourismSpot.getType();
        this.foreignerPrice = tourismSpot.getForeignerPrice();
        this.egyptianPrice = tourismSpot.getEgyptianPrice();
        this.openingTime = tourismSpot.getOpeningTime();
        this.closingTime = tourismSpot.getClosingTime();
        this.image = tourismSpot.getImage();
        this.address = tourismSpot.getAddress().getName();
    }
}
