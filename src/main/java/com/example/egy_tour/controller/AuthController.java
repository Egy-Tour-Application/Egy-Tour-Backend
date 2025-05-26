package com.example.egy_tour.controller;

import com.example.egy_tour.dto.LoginRequestDTO;
import com.example.egy_tour.dto.LoginResponseDTO;
import com.example.egy_tour.dto.RegisterRequestDTO;
import com.example.egy_tour.model.User;
import com.example.egy_tour.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        User user = authService.registerUser(registerRequest);
        if (user == null) {
            return ResponseEntity.badRequest().body("An account with this email already exists!");
        }
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }
}