package com.example.homework31.repository;

import com.example.homework31.domain.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void testRepositoryMethods() {
        String title = "Test title";
        Long authorId = 1L;
        Long genreId = 1L;
        Long commentId = 1L;

        bookRepository.saveBook(title, authorId, genreId, commentId);

        BookEntity book = bookRepository.findByTitle(title);
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getAuthor().getId()).isEqualTo(authorId);
        assertThat(book.getGenre().getId()).isEqualTo(genreId);
        assertThat(book.getComment().getId()).isEqualTo(commentId);
    }
}
