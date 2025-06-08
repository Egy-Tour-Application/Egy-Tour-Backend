package com.example.egy_tour.repository;

import com.example.egy_tour.model.ChatbotHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatbotHistoryRepository extends JpaRepository<ChatbotHistory, Long> {


    List<ChatbotHistory> findByUserIdOrderByTimeAsc(Long userId);
}
