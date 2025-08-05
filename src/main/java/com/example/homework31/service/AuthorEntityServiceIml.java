package com.example.homework31.service;

import com.example.homework31.domain.AuthorEntity;
import com.example.homework31.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorEntityServiceIml implements AuthorEntityService {
    private final AuthorRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AuthorEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorEntity findByName(String name) {
        return repository.findByName(name);
    }
    @Transactional
    @Override
    public void saveByName(String name) {
        repository.saveByName(name);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
