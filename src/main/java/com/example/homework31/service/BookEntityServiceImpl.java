package com.example.homework31.service;

import com.example.homework31.domain.AuthorEntity;
import com.example.homework31.domain.BookEntity;
import com.example.homework31.domain.CommentEntity;
import com.example.homework31.domain.GenreEntity;
import com.example.homework31.repository.AuthorRepository;
import com.example.homework31.repository.BookRepository;
import com.example.homework31.repository.CommentRepository;
import com.example.homework31.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookEntityServiceImpl implements BookEntityService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public BookEntity save(String title, String genre, String author, String comment) {
        GenreEntity genreEntity = getGenre(genre);
        AuthorEntity authorEntity = getAuthor(author);
        CommentEntity commentEntity = getComment(comment);
        bookRepository.saveBook(title, authorEntity.getId(), genreEntity.getId(), commentEntity.getId());
        return bookRepository.findByTitle(title);
    }

    private GenreEntity getGenre(String genre) {
        GenreEntity genreEntity = genreRepository.findByName(genre);
        if (genreEntity == null) {
            genreRepository.saveByName(genre);
            genreEntity = genreRepository.findByName(genre);
        }
        return genreEntity;
    }

    private AuthorEntity getAuthor(String author) {
        AuthorEntity authorEntity = authorRepository.findByName(author);
        if (authorEntity == null) {
            authorRepository.saveByName(author);
            authorEntity = authorRepository.findByName(author);
        }
        return authorEntity;
    }

    private CommentEntity getComment(String comment) {
        CommentEntity commentEntity = commentRepository.findByComment(comment);
        if (commentEntity == null) {
            commentRepository.saveByComment(comment);
            commentEntity = commentRepository.findByComment(comment);
        }
        return commentEntity;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BookEntity update(String title, String genre, String author, String comment) {
        BookEntity bookFromDb = bookRepository.findByTitle(title);
        if (bookFromDb == null) {
            return save(title, genre, author, comment);
        }
        updateGenre(bookFromDb, genre);
        updateAuthor(bookFromDb, author);
        updateComment(bookFromDb, comment);

        return bookRepository.save(bookFromDb);
    }

    private void updateGenre(BookEntity bookFromDb, String genre) {
        if (Objects.equals(bookFromDb.getGenre().getName(), genre)) {
            return;
        }
        GenreEntity genreEntity = genreRepository.findByName(genre);
        if (genreEntity == null) {
            genreRepository.saveByName(genre);
            genreEntity = genreRepository.findByName(genre);
        }
        bookFromDb.setGenre(genreEntity);
    }

    private void updateAuthor(BookEntity bookFromDb, String author) {
        if (Objects.equals(bookFromDb.getAuthor().getName(), author)) {
            return;
        }
        AuthorEntity authorEntity = authorRepository.findByName(author);
        if (authorEntity == null) {
            authorRepository.saveByName(author);
            authorEntity = authorRepository.findByName(author);
        }
        bookFromDb.setAuthor(authorEntity);
    }

    private void updateComment(BookEntity bookFromDb, String comment) {
        if (Objects.equals(bookFromDb.getComment().getComment(), comment)) {
            return;
        }
        CommentEntity commentEntity = commentRepository.findByComment(comment);
        if (commentEntity == null) {
            commentRepository.saveByComment(comment);
            commentEntity = commentRepository.findByComment(comment);
        }
        bookFromDb.setComment(commentEntity);
    }

}
