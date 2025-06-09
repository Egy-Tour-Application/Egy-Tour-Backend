package com.example.egy_tour.service;

import com.example.egy_tour.dto.ChatbotMessageDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
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

    @Autowired
    public ChatbotService(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    public String getChatbotResponse(ChatbotMessageDTO messageDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put(ChatMemory.CONVERSATION_ID, messageDTO.getUserId().toString());

        String response = chatClient.prompt()
                .advisors(a -> a.params(map))
                .user(messageDTO.getQuestion())
                .call().content();

        if (response == null) {
            return "I don't know";
        }
        int start = response.indexOf("</think>") + 9;
        return response.substring(start).trim();
    }

    public void addVector(String documentContent, String type, Long id) {
        Map<String, Object> metadata = Map.of(
                "type", type,
                "id", id.toString()
        );
        vectorStore.add(
                List.of(new Document(documentContent, metadata))
        );
    }

    public void deleteVector(String type, Long id) {
        FilterExpressionBuilder feb = new FilterExpressionBuilder();
        Filter.Expression expression = feb.and(
                feb.eq("type", type),
                feb.eq("id", id.toString())
        ).build();
        vectorStore.delete(expression);
    }

    public void updateVector(String documentContent, String type, Long id) {
        deleteVector(type, id);
        addVector(documentContent, type, id);
    }
}
