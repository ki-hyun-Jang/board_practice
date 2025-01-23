package com.example.board.author;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.author.domain.dto.AuthorSaveReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest(){
        AuthorSaveReq authorSaveReq = AuthorSaveReq.builder().name("hongkkkkki").email("abcabc66tfa@naver.com").password("213131312321").role(Role.USER).build();
        authorService.save(authorSaveReq);
        Author author = authorRepository.findByEmail("abcabc66tfa@naver.com").orElse(null);
        Assertions.assertEquals(authorSaveReq.getEmail(), author.getEmail());
    }

    @Test
    public void findAllTest(){
        int beforeSize = authorService.findAll().size();
        authorRepository.save(Author.builder().name("abb").email("aassad1@asasdas.dsadas").password("sadsad123").build());
        authorRepository.save(Author.builder().name("abb").email("aassad2@asasdas.dsadas").password("sadsad123").build());
        authorRepository.save(Author.builder().name("abb").email("aassad3@asasdas.dsadas").password("sadsad123").build());
        int afterSize = authorService.findAll().size();
        Assertions.assertEquals(beforeSize+3,afterSize);
    }
}
