package com.fabien.auth_service.security;

import com.fabien.auth_service.client.UserClient;
import com.fabien.auth_service.dto.UserDto;
import com.fabien.common.security.UserDetailsServiceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsServiceAdapter, UserDetailsService {

    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Entree dans loadUserByName");
        UserDto user = userClient.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found :" + username);
        }
        log.debug("Récupération d'un user : {}", user != null);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
