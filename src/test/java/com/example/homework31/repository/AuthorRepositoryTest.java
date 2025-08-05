package com.example.homework31.repository;

import com.example.homework31.domain.AuthorEntity;
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
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Transactional
    void testRepositoryMethods() {
        String authorName = "Test name";
        authorRepository.saveByName(authorName);

        AuthorEntity author = authorRepository.findByName(authorName);
        assertThat(author).isNotNull();
        assertThat(author.getName()).isEqualTo(authorName);
    }
}
