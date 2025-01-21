package com.example.board.author.controller;

import com.example.board.author.domain.dto.AuthorDetailRes;
import com.example.board.author.domain.dto.AuthorListRes;
import com.example.board.author.domain.dto.AuthorSaveReq;
import com.example.board.author.domain.dto.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
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
    public String findAll(){
        List<AuthorListRes> authorListResList = authorService.findAll();
        return "author/author_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return "ok";
    }

    @GetMapping("/detail/{id}")
    public AuthorDetailRes detailAuthor(@PathVariable Long id){
        return authorService.authorDetail(id);
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id, @Valid AuthorUpdateReq authorUpdateReq){
        authorService.updateAuthor(id, authorUpdateReq);
        return "ok";
    }

}
