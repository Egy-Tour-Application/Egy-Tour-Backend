package com.example.egy_tour.controller;
import com.example.egy_tour.dto.ChatHistoryMessageDTO;
import com.example.egy_tour.dto.ChatbotMessageDTO;
import com.example.egy_tour.dto.CreateVectorDTO;
import com.example.egy_tour.service.ChatbotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askChatbot(@Valid @RequestBody ChatbotMessageDTO messageDTO) {
        return ResponseEntity.ok(chatbotService.getChatbotResponse(messageDTO));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ChatHistoryMessageDTO>> getChatHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(chatbotService.getChatHistory(userId));
    }

    @PostMapping("/vector")
    public ResponseEntity<?> addVector(@Valid @RequestBody CreateVectorDTO vectorDTO) {
        chatbotService.createVector(vectorDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/vector/list")
    public ResponseEntity<?> addVector(@Valid @RequestBody CreateVectorDTO[] vectorDTOs) {
        for (CreateVectorDTO vectorDTO : vectorDTOs) {
            chatbotService.createVector(vectorDTO);
        }
        return ResponseEntity.ok().build();
    }
}
