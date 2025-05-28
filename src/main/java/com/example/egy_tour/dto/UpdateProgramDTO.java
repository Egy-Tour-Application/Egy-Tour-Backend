package com.example.egy_tour.dto;

import com.example.egy_tour.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProgramDTO {
    private List<Long> bookingIds;

}
