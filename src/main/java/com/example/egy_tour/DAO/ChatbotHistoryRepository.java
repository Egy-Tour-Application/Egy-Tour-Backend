package com.example.egy_tour.DAO;

import com.example.egy_tour.model.entity.ChatbotHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotHistoryRepository extends JpaRepository<ChatbotHistory, Integer> {

}
