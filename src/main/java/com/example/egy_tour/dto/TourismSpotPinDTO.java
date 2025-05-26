package com.example.egy_tour.dto;
import com.example.egy_tour.model.TourismSpot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourismSpotPinDTO {
    private String name;
    private double egyptianPrice;
    private double foreignerPrice;
    private String openingTime;
    private String closingTime;
    private double latitude;
    private double longitude;
    private String image;

    public TourismSpotPinDTO(TourismSpot tourismSpot) {
        this.name = tourismSpot.getTitle();
        this.egyptianPrice = tourismSpot.getEgyptianPrice();
        this.foreignerPrice = tourismSpot.getForeignerPrice();
        this.latitude = tourismSpot.getLatitude();
        this.longitude = tourismSpot.getLongitude();
        this.openingTime = tourismSpot.getOpeningTime();
        this.closingTime = tourismSpot.getClosingTime();
        this.image = tourismSpot.getImage();
    }
}
