package com.example.egy_tour.DAO;

import com.example.egy_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
