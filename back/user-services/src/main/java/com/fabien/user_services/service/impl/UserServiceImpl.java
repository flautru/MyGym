package com.fabien.user_services.service.impl;

import com.fabien.user_services.model.User;
import com.fabien.user_services.repository.UserRepository;
import com.fabien.user_services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User users) {
        return userRepository.save(users);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}