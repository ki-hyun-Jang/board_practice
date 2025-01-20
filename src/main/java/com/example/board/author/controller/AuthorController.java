package com.example.board.author.controller;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.dto.AuthorListRes;
import com.example.board.author.domain.dto.AuthorSaveReq;
import com.example.board.author.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    AuthorService authorService ;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public String author(@Valid AuthorSaveReq dto){
        authorService.save(dto);
        return "ok";
    }

    @GetMapping("/list")
    public List<AuthorListRes> findAll(){
        return authorService.findAll();
    }
}
