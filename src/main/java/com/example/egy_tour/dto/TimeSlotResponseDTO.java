package com.example.egy_tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotResponseDTO {
    private Long id;
    private LocalDateTime  startTime;
    private LocalDateTime endTime;

}
