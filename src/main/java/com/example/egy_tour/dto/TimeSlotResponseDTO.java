package com.example.egy_tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotResponseDTO {
    private Long id;
    private LocalDate startTime;
    private LocalDate endTime;

}
