package com.example.egy_tour.service;

import com.example.egy_tour.dto.ChatHistoryMessageDTO;
import com.example.egy_tour.dto.ChatbotMessageDTO;
import com.example.egy_tour.dto.CreateVectorDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.document.Document;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatbotService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final ChatMemoryRepository chatMemoryRepository;
    private final UserService userService;

    @Autowired
    public ChatbotService(ChatClient chatClient, VectorStore vectorStore, ChatMemoryRepository chatMemoryRepository, UserService userService) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
        this.chatMemoryRepository = chatMemoryRepository;
        this.userService = userService;
    }

    public String getChatbotResponse(ChatbotMessageDTO messageDTO) {
        Map<String, Object> advisorParams = new HashMap<>();
        advisorParams.put(ChatMemory.CONVERSATION_ID, messageDTO.getUserId().toString());

        Map<String, Object> systemParams = new HashMap<>();
        systemParams.put("userInfo", userService.getUserInformation(messageDTO.getUserId()));

        String response = chatClient.prompt()
                .system(sp -> sp.params(systemParams))
                .advisors(a -> a.params(advisorParams))
                .user(messageDTO.getQuestion())
                .call().content();

        if (response == null) {
            return "I don't currently have an answer for that question.";
        }
        int start = response.indexOf("</think>") + 9;
        return response.substring(start).trim();
    }

    public void createVector(CreateVectorDTO vectorDTO) {
        Map<String, Object> metadata = Map.of(
                "type", vectorDTO.getType(),
                "id", vectorDTO.getId()
        );
        vectorStore.add(
                List.of(new Document(vectorDTO.getDocumentContent(), metadata))
        );
    }

    public void deleteVector(String type, String id) {
        FilterExpressionBuilder feb = new FilterExpressionBuilder();
        Filter.Expression expression = feb.and(
                feb.eq("type", type),
                feb.eq("id", id)
        ).build();
        vectorStore.delete(expression);
    }

    public void updateVector(CreateVectorDTO vectorDTO) {
        deleteVector(vectorDTO.getType(), vectorDTO.getId());
        createVector(vectorDTO);
    }

    public List<ChatHistoryMessageDTO> getChatHistory(Long userId) {
        List<Message> messages = chatMemoryRepository.findByConversationId(userId.toString());
        return messages.stream()
                .map(m ->
                {
                    String type = m.getMessageType().toString();
                    String message = m.getText();
                    if (type.equals("ASSISTANT")) {
                        int start = m.getText().indexOf("</think>") + 9;
                        message = message.substring(start).trim();
                    }
                    return new ChatHistoryMessageDTO(type, message);
                })
                .toList();
    }
}
