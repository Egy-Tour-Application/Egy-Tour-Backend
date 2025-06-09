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
public class ChatHistoryMessageDTO {
    @NotBlank
    private String type;

    @NotBlank
    private String message;
}