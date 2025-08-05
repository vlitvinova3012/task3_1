package com.example.homework31.service;


import com.example.homework31.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentEntityService {
    List<CommentEntity> findAll();

    Optional<CommentEntity> findById(Long id);

    void deleteById(Long id);

    CommentEntity findByComment(String comment);

    void saveByComment(String comment);
}
