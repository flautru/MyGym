package com.fabien.auth_service.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;
    private String mail;
    private Integer statut;
}
