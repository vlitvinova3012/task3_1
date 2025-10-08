package com.example.homework31.service;

import com.example.homework31.controller.dto.UserDto;
import com.example.homework31.domain.UserEntity;
import com.example.homework31.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<UserDto> saveUser(UserDto dto) {
        UserEntity entity = new UserEntity();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        UserEntity savedUser = userRepository.save(entity);
        dto.setPassword("******");
        dto.setUserId(savedUser.getId());
        return ResponseEntity.ok(dto);
    }

    public boolean findByUsername(String username) {
        UserEntity byUsername = userRepository.findByUsername(username);
        if (byUsername != null) {
            return true;
        }
        return false;
    }
}
