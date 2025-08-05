package com.example.homework31.repository;

import com.example.homework31.domain.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO Author (name) VALUES (:name)", nativeQuery = true)
    void saveByName(@Param("name") String name);

    AuthorEntity findByName(String name);
}
