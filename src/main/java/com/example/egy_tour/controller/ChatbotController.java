package com.example.egy_tour.controller;
import com.example.egy_tour.dto.ChatbotMessageDTO;
import com.example.egy_tour.service.ChatbotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
