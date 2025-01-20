package com.example.board.author.repository;

import com.example.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    public Optional<Author> findByEmail(String email);
}
