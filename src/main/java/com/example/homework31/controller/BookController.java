package com.example.homework31.controller;

import com.example.homework31.domain.AuthorEntity;
import com.example.homework31.domain.BookEntity;
import com.example.homework31.domain.CommentEntity;
import com.example.homework31.domain.GenreEntity;
import com.example.homework31.service.BookEntityService;
import com.example.homework31.util.RESTStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookEntityService bookEntityService;

    @GetMapping("/books")
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> result = bookEntityService.findAll();
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
        BookEntity result = bookEntityService.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<BookEntity> createBookByParams(String title, String genre, String author, String comment) {
        BookEntity result = bookEntityService.save(title, genre, author, comment);
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PutMapping("/book")
    public ResponseEntity<BookEntity> updateBook(@RequestBody(required = true)
                                                 BookEntity book) {
        String genre = Optional.ofNullable(book.getGenre()).map(GenreEntity::getName).orElse(null);
        String author = Optional.ofNullable(book.getAuthor()).map(AuthorEntity::getName).orElse(null);
        String comment = Optional.ofNullable(book.getComment()).map(CommentEntity::getComment).orElse(null);
        BookEntity result = bookEntityService.update(book.getTitle(), genre, author, comment);

        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {

        bookEntityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
