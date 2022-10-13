package com.example.spartahomework2.domain.repository;

import com.example.spartahomework2.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p.author from Post p where p.id = :id")
    Optional<String> findAuthorById(@Param("id") Long id);

}
