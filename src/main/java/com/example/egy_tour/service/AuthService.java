package com.example.egy_tour.service;

import com.example.egy_tour.dto.LoginRequestDTO;
import com.example.egy_tour.dto.LoginResponseDTO;
import com.example.egy_tour.dto.RegisterRequestDTO;
import com.example.egy_tour.model.User;
import com.example.egy_tour.repository.UserRepository;
import com.example.egy_tour.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    public User registerUser(RegisterRequestDTO registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return null;
        }
        registerRequest.setPassword(
                encoder.encode(registerRequest.getPassword())
        );
        return userRepository.save(registerRequest.toUser());
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        return new LoginResponseDTO(token, userDetails.getId());
    }
}