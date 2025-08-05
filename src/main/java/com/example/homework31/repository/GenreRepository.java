package com.example.homework31.repository;

import com.example.homework31.domain.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO Genre (name) VALUES (:name)", nativeQuery = true)
    void saveByName(@Param("name") String name);

    GenreEntity findByName(String name);

}
