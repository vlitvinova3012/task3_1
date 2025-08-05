package com.example.homework31.controller;

import com.example.homework31.domain.AuthorEntity;
import com.example.homework31.service.AuthorEntityService;
import com.example.homework31.util.RESTStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorEntityService authorEntityService;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
        List<AuthorEntity> result = authorEntityService.findAll();
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable Long id) {
        AuthorEntity result = authorEntityService.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/author/{name}")
    public ResponseEntity<AuthorEntity> getAuthorByName(@PathVariable String name) {
        AuthorEntity result = authorEntityService.findByName(name);

        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PostMapping("/author")
    public ResponseEntity<Void> createAuthorByName(String name) {
        authorEntityService.saveByName(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {

        authorEntityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
