package com.example.egy_tour.repository;

import com.example.egy_tour.model.Program;
import com.example.egy_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByUser(User user);
}
