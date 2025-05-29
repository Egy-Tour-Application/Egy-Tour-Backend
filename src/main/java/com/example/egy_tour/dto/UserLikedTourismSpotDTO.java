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
public class UserLikedTourismSpotDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Long tourismSpotId;
}
