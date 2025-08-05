package com.example.homework31.repository;

import com.example.homework31.domain.CommentEntity;
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
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Transactional
    void testRepositoryMethods() {
        String commentText = "test comment";
        commentRepository.saveByComment(commentText);

        CommentEntity comment = commentRepository.findByComment(commentText);
        assertThat(comment).isNotNull();
        assertThat(comment.getComment()).isEqualTo(commentText);
    }
}
