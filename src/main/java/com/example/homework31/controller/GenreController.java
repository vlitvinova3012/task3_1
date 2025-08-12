package com.example.homework31.controller;

import com.example.homework31.domain.GenreEntity;
import com.example.homework31.service.GenreEntityService;
import com.example.homework31.util.RESTStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreEntityService genreEntityService;

    @GetMapping("/genres")
    public ResponseEntity<List<GenreEntity>> getAllGenres() {
        List<GenreEntity> result = genreEntityService.findAll();
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/genre/id/{id}")
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        GenreEntity result = genreEntityService.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/genre/name/{name}")
    public ResponseEntity<GenreEntity> getGenreByName(@PathVariable String name) {
        GenreEntity result = genreEntityService.findByName(name);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PostMapping("/genre")
    public ResponseEntity<Void> createGenreByParams(String name) {
        genreEntityService.saveByName(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/genre/{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable Long id) {
        genreEntityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
