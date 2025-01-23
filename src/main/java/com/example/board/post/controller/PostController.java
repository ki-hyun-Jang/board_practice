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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//1)글쓰기(post/create)
//  -title, contents, email 작성 (form-data)
//  -DTO명 : PostSaveReq
//2)글목록조회(post/list)
//  -id, title, author_email return (json)
//        -DTO명 : PostListRes
@Controller
@RequestMapping("/post")
public class PostController {

    PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String viewPostCreate(){
        return "/post/post_create";
    }

    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq dto){
        postService.save(dto);
        return "redirect:/post/list/paging";
    }

    @GetMapping("/list")
    public String postList(Model model){
        List<PostListRes> postListResList = postService.findAll();
        model.addAttribute("postListResList",postListResList);
        return "/post/post_list";
    }

    @GetMapping("detail/{id}")
    public String postDetailById(@PathVariable Long id, Model model){
        PostDetailRes postDetailRes = postService.findById(id);
        model.addAttribute("postDetailRes",postDetailRes);
        return "/post/post_detail";
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
    public String postListPaging(Model model,
                                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("postListResList", postService.findAllPaging(pageable));
        return "post/post_list";
    }

    @GetMapping("/list/fetchjoin")
    @ResponseBody
    public String postListFetchJoin(){
        postService.listFetchJoin();
        return "OK";
    }
}
