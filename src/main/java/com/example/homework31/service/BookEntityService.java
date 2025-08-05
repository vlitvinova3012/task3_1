package com.example.homework31.service;



import com.example.homework31.domain.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookEntityService {
    List<BookEntity> findAll();

    Optional<BookEntity> findById(Long id);

    BookEntity save(String title, String genre, String author, String comment);

    void deleteById(Long id);

    BookEntity update(String title, String genre, String author, String comment);
}
