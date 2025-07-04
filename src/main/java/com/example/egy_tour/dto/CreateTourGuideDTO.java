package com.example.egy_tour.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTourGuideDTO {
    @NotNull
    private Long userId;

    @NotNull
    private double price;
}
