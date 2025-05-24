package com.example.egy_tour.repository;

import com.example.egy_tour.model.ChatbotHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotHistoryRepository extends JpaRepository<ChatbotHistory, Long> {

}
