package com.fabien.auth_service.client;

import com.fabien.auth_service.dto.RegisterRequest;
import com.fabien.auth_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/by-username")
    UserDto getByUsername(@RequestParam String username);

    @PostMapping("/api/users/register")
    UserDto register(@RequestBody RegisterRequest request);
}
