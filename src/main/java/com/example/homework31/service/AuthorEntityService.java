package com.example.homework31.service;



import com.example.homework31.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorEntityService {
    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findById(Long id);

    void saveByName(String name);

    AuthorEntity findByName(String name);

    void deleteById(Long id);
}
