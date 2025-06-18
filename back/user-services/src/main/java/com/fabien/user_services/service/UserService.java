package com.fabien.user_services.service;

import com.fabien.user_services.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveUser(User Users);
    void deleteUser(Long id);
}
