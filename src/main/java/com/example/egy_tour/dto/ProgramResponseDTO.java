package com.example.egy_tour.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> bookingIds;
}
