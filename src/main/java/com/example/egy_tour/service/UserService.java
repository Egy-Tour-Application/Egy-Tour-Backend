package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateUserDTO;
import com.example.egy_tour.model.User;
import com.example.egy_tour.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
