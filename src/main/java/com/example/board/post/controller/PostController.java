package com.example.board.post.controller;


import com.example.board.post.domain.dto.PostListRes;
import com.example.board.post.domain.dto.PostSaveReq;
import com.example.board.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//1)글쓰기(post/create)
//  -title, contents, email 작성 (form-data)
//  -DTO명 : PostSaveReq
//2)글목록조회(post/list)
//  -id, title, author_email return (json)
//        -DTO명 : PostListRes
@RestController
@RequestMapping("/post")
public class PostController {

    PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq dto){
        postService.save(dto);
        return "ok";
    }

    @GetMapping("/list")
    public List<PostListRes> postList(){
        return postService.findAll();
    }
}
