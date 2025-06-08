package com.example.egy_tour.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.memory.repository.jdbc.PostgresChatMemoryRepositoryDialect;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ChatbotConfig {

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel chatModel, QuestionAnswerAdvisor questionAnswerAdvisor, MessageChatMemoryAdvisor messageChatMemoryAdvisor) {
        return ChatClient.builder(chatModel).defaultAdvisors(
                questionAnswerAdvisor, messageChatMemoryAdvisor
        ).build();
    }

    @Bean
    public MessageChatMemoryAdvisor messageChatMemoryAdvisor(JdbcTemplate jdbcTemplate) {
        ChatMemoryRepository chatMemoryRepository = JdbcChatMemoryRepository.builder()
                .jdbcTemplate(jdbcTemplate)
                .dialect(new PostgresChatMemoryRepositoryDialect())
                .build();

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();

        return MessageChatMemoryAdvisor.builder(chatMemory).build();
    }

    @Bean
    QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
        return QuestionAnswerAdvisor.builder(vectorStore)
                .searchRequest(
                        SearchRequest.builder().similarityThreshold(0.5).topK(10).build()
                )
                .build();
    }
}
