package com.fabien.auth_service.controller;

import com.fabien.auth_service.client.UserClient;
import com.fabien.auth_service.dto.JwtResponse;
import com.fabien.auth_service.dto.LoginRequest;
import com.fabien.auth_service.dto.RegisterRequest;
import com.fabien.auth_service.dto.UserDto;
import com.fabien.auth_service.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserClient userClient;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            log.warn("ACCES ENDPOINT LOGIN");
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            //            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateJwtToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception ex) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        // appel du user-service pour créer l'utilisateur
        log.warn("ACCES ENDPOINT REGISTER");
        UserDto createdUser = userClient.register(request);

        log.warn("RETOUR USER SERVICE : {}", createdUser.getUsername());
        return ResponseEntity.ok(createdUser);
    }
}