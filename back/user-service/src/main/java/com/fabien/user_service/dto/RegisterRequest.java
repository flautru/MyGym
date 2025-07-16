package com.fabien.user_service.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String mail;
    private String password;
    private String adress;
    private String phoneNumber;
    private Integer statut;
    private String role;
}