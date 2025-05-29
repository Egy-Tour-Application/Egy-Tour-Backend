package com.example.egy_tour.controller;

import com.example.egy_tour.dto.AddUserPreferenceDTO;
import com.example.egy_tour.dto.UpdateUserDTO;
import com.example.egy_tour.dto.UserResponseDTO;
import com.example.egy_tour.model.User;
import com.example.egy_tour.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        System.out.println("Received request to get user with ID: " + id);
        User user = userService.getUserById(id);

        if (user != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getNationality(),
                    user.getGender()
            );
            return ResponseEntity.ok(userResponseDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("Received request to get all users");
        List<User> users = userService.getAllUsers();
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/add-preference")
    public ResponseEntity<Boolean> updateUser(@Valid @RequestBody AddUserPreferenceDTO addUserPreferenceDTO) {
        return ResponseEntity.ok(userService.addUserPreference(addUserPreferenceDTO));
    }


}
