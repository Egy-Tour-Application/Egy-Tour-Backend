package com.example.egy_tour.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long tourGuideId;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time must be in the present or future")
    private LocalDateTime endTime;
}
