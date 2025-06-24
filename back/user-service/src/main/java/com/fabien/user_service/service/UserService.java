package com.fabien.user_service.service;

import com.fabien.user_service.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveUser(User Users);
    void deleteUser(Long id);
}
