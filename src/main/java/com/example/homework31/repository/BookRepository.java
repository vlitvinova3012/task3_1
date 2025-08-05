package com.example.homework31.repository;

import com.example.homework31.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO Book (title, author_id, genre_id, comment_id) VALUES (:title, :author_id, :genre_id, :comment_id)", nativeQuery = true)
    void saveBook(@Param("title") String title,
                  @Param("author_id") Long authorId,
                  @Param("genre_id") Long genreId,
                  @Param("comment_id") Long commentId);

    BookEntity findByTitle(String title);
}
