package com.example.egy_tour.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVectorDTO {
    @NotBlank
    String id;

    @NotBlank
    String type;

    @NotBlank
    String documentContent;
}
