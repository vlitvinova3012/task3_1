package com.example.homework31.controller;

import com.example.homework31.domain.CommentEntity;
import com.example.homework31.service.CommentEntityService;
import com.example.homework31.util.RESTStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentEntityService commentEntityService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentEntity>> getAllComments() {
        List<CommentEntity> result = commentEntityService.findAll();
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentEntity> getCommentById(@PathVariable Long id) {
        CommentEntity result = commentEntityService.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/comment/{comment}")
    public ResponseEntity<CommentEntity> getCommentByParam(@PathVariable String comment) {
        CommentEntity result = commentEntityService.findByComment(comment);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if (result instanceof RESTStatus) {
            return ResponseEntity.status(((RESTStatus) result).getHttpStatus()).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> createCommentByParams(String comment) {
        commentEntityService.saveByComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentEntityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
