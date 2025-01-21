package com.example.board.post.controller;


import com.example.board.post.domain.dto.PostDetailRes;
import com.example.board.post.domain.dto.PostListRes;
import com.example.board.post.domain.dto.PostSaveReq;
import com.example.board.post.domain.dto.PostUpdateReq;
import com.example.board.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("detail/{id}")
    public PostDetailRes postDetailById(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping("update/{id}")
    public String updatePost(@PathVariable Long id, PostUpdateReq postUpdateReq){
        postService.updatePost(id, postUpdateReq);
        return "ok";
    }

    @PostMapping("delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "ok";
    }

    @GetMapping("/list/paging")
//    페이징처리를 위한 데이터 형식: localhost:8080/post/list/paging?size=10&page=0&sort=createdTime,desc
    public Page<PostListRes> postListPaging(@PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){
        return postService.findAllPaging(pageable);
    }
}
