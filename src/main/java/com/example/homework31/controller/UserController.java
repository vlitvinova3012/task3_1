package com.example.homework31.controller;

import com.example.homework31.controller.dto.UserDto;
import com.example.homework31.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        if (Objects.isNull(userDto)) {
            throw new IllegalArgumentException("Payload cannot be Null");
        }
        if (userService.findByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        return userService.saveUser(userDto);
    }
}
