package com.example.egy_tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourGuideResponseDTO {
    private Long id;
    private double price;
    private UserResponseDTO user;
    private List<TimeSlotResponseDTO> timeSlots;
}
