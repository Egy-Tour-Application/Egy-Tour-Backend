package com.example.egy_tour.service;

import com.example.egy_tour.dto.ChatbotMessageDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
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

        return chatClient.prompt("You are a helpful travel assistant. " +
                        "Answer the user's questions about tourism in Egypt. " +
                        "If you don't know the answer, say 'I don't know'. " +
                        "If the user asks for a specific tourism spot, provide information about it.")
                .advisors(a -> a.params(map))
                .user(messageDTO.getQuestion()).call().content();
    }

    public void addVector(String documentContent) {
        vectorStore.write(
                List.of(new Document(documentContent)
                )
        );
    }
}
