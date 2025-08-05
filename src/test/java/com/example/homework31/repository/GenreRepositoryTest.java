package com.example.homework31.repository;

import com.example.homework31.domain.GenreEntity;
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
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @Transactional
    void testRepositoryMethods() {
        String genreName = "Test genre";
        genreRepository.saveByName(genreName);

        GenreEntity genre = genreRepository.findByName(genreName);
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo(genreName);
    }
}
