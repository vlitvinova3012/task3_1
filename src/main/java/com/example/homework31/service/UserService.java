package com.example.homework31.service;

import com.example.homework31.controller.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> saveUser(UserDto dto);

    boolean findByUsername(String username);
}
