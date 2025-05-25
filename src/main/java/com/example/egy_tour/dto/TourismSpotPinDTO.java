package com.example.egy_tour.dto;
import com.example.egy_tour.model.TourismSpot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourismSpotPinDTO {
    private String title;
    private String description;
    private double egyptianPrice;
    private double foreignerPrice;
    private String address;
    private Date openingTime;
    private Date closingTime;
    private double latitude;
    private double longitude;
    private String image;

    public TourismSpotPinDTO(TourismSpot tourismSpot) {
        this.title = tourismSpot.getTitle();
        this.description = tourismSpot.getDescription();
        this.egyptianPrice = tourismSpot.getEgyptianPrice();
        this.foreignerPrice = tourismSpot.getForeignerPrice();
        address = tourismSpot.getAddress().getFullAddressName();
        this.latitude = tourismSpot.getLatitude();
        this.longitude = tourismSpot.getLongitude();
        this.openingTime = tourismSpot.getOpeningTime();
        this.closingTime = tourismSpot.getClosingTime();
        this.image = tourismSpot.getImage();
    }
}
