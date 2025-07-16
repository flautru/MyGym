package com.fabien.user_service.mapper;

import com.fabien.user_service.dto.RegisterRequest;
import com.fabien.user_service.model.User;

public class UserMapper {

    public static User toEntity(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setMail(registerRequest.getMail());
        user.setPassword(registerRequest.getPassword());
        user.setAdress(registerRequest.getAdress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setStatut(registerRequest.getStatut());

        return user;
    }
}
