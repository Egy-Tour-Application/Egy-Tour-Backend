package com.example.egy_tour.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTimeSlotDTO {

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private LocalDate startTime;

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time must be in the present or future")
    private LocalDate endTime;

    @NotNull(message = "Address ID is required")
    private Long addressId;
}
