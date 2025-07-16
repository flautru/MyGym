package com.fabien.common.security;

public interface JwtService {

    String getUsernameFromJwtToken(String token);

    boolean validateJwtToken(String token);

    String generateJwtToken(org.springframework.security.core.Authentication authentication);
}
