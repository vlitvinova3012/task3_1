package com.example.homework31.repository;

import com.example.homework31.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO Comment (comment) VALUES (:comment)", nativeQuery = true)
    void saveByComment(@Param("comment") String comment);

    CommentEntity findByComment(String comment);
}
