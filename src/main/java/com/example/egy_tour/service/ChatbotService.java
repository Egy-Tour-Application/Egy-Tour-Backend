package com.example.egy_tour.service;

import com.example.egy_tour.model.ChatbotHistory;
import com.example.egy_tour.repository.ChatbotHistoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.memory.repository.jdbc.MysqlChatMemoryRepositoryDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotService {

    private final ChatbotHistoryRepository chatbotHistoryRepository;
    private final ChatClient chatClient;


    @Autowired
    public ChatbotService(ChatbotHistoryRepository chatbotHistoryRepository, ChatClient chatClient) {
        this.chatbotHistoryRepository = chatbotHistoryRepository;
        this.chatClient = chatClient;
    }


    public String getChatbotResponse(String userMessage) {

        List<ChatbotHistory> history = chatbotHistoryRepository.findByUserIdOrderByTimeAsc(1L);


        String response = chatClient.prompt("You are a helpful travel assistant. " +
                        "Answer the user's questions about tourism in Egypt. " +
                        "If you don't know the answer, say 'I don't know'. " +
                        "If the user asks for a specific tourism spot, provide information about it.")
                .advisors()
                .user(userMessage).call().content();







        return response;


    }


}
