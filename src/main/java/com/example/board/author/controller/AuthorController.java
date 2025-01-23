package com.example.board.author.controller;

import com.example.board.author.domain.dto.AuthorDetailRes;
import com.example.board.author.domain.dto.AuthorListRes;
import com.example.board.author.domain.dto.AuthorSaveReq;
import com.example.board.author.domain.dto.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/create")
    public String viewAuthorCreate(){
        return "/author/author_create";
    }

    @PostMapping("/create")
    public String author(@Valid AuthorSaveReq dto){
        authorService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<AuthorListRes> authorListResList = authorService.findAll();
        model.addAttribute("authorList",authorListResList);
        return "/author/author_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return "ok";
    }

    @GetMapping("/detail/{id}")
    public String detailAuthor (@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.authorDetail(id));
//        "author"라는 변수에 데이터 세팅해서 화면 리턴
        return "/author/author_detail";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id, @Valid AuthorUpdateReq authorUpdateReq){
        authorService.updateAuthor(id, authorUpdateReq);
        return "ok";
    }

}
