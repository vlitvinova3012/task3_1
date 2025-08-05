package com.example.homework31.service;

import com.example.homework31.domain.GenreEntity;
import com.example.homework31.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreEntityServiceImpl implements GenreEntityService {
    private final GenreRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<GenreEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<GenreEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public GenreEntity findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    @Override
    public void saveByName(String name) {
        repository.saveByName(name);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
