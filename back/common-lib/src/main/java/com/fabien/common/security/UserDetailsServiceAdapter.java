package com.fabien.common.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsServiceAdapter {

    UserDetails loadUserByUsername(String username);
}
