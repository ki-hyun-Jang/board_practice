package com.example.board.post.service;

import com.example.board.author.domain.Author;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.post.domain.Post;
import com.example.board.post.domain.dto.PostListRes;
import com.example.board.post.domain.dto.PostSaveReq;
import com.example.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

//1)글쓰기(post/create)
//  -title, contents, email 작성 (form-data)
//  -DTO명 : PostSaveReq
//2)글목록조회(post/list)
//  -id, title, author_email return (json)
//        -DTO명 : PostListRes
@Service
@Transactional
public class PostService {

    PostRepository postRepository;
    AuthorRepository authorRepository;

    @Autowired
    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public void save(PostSaveReq dto){
        Author author = authorRepository.findByEmail(dto.getEmail()).orElseThrow(()->new EntityNotFoundException("없는 이메일임"));
        postRepository.save(dto.toEntity(author));
    }

    public List<PostListRes> findAll(){
        return postRepository.findAll().stream().map(Post::postListResFromEntity).collect(Collectors.toList());
    }
}
