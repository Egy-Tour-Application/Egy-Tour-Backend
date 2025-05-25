package com.example.egy_tour.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {
    @NotBlank(message = "Name of Address in required")
    private String name;
    private String parentName;
}
