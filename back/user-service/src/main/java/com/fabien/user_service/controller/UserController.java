package com.fabien.user_service.controller;

import com.fabien.user_service.dto.RegisterRequest;
import com.fabien.user_service.mapper.UserMapper;
import com.fabien.user_service.model.User;
import com.fabien.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User users) {
        return userService.saveUser(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User users) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            users.setId(id);
            return ResponseEntity.ok(userService.saveUser(users));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest registerRequest) {
        return userService.saveUser(UserMapper.toEntity(registerRequest));
    }
}