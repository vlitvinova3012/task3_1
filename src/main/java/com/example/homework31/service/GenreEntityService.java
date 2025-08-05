package com.example.homework31.service;


import com.example.homework31.domain.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreEntityService {
    List<GenreEntity> findAll();

    Optional<GenreEntity> findById(Long id);

    void deleteById(Long id);

    GenreEntity findByName(String name);

    void saveByName(String name);
}
