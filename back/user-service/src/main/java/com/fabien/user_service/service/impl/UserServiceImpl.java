package com.fabien.user_service.service.impl;

import com.fabien.logging.KafkaLogProducer;
import com.fabien.user_service.model.User;
import com.fabien.user_service.repository.UserRepository;
import com.fabien.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final KafkaLogProducer kafkaLogProducer;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(KafkaLogProducer kafkaLogProducer) {
        this.kafkaLogProducer = kafkaLogProducer;
    }

    public List<User> getAllUsers() {
        kafkaLogProducer.sendLog("user-services", "INFO", "Demande de tous les utilisateurs");
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