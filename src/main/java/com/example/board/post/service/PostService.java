package com.example.board.post.service;

import com.example.board.author.domain.Author;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.post.domain.Post;
import com.example.board.post.domain.dto.PostDetailRes;
import com.example.board.post.domain.dto.PostListRes;
import com.example.board.post.domain.dto.PostSaveReq;
import com.example.board.post.domain.dto.PostUpdateReq;
import com.example.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime appointmentTime = null;
        if (dto.getAppointment().equals("Y")){
            if (dto.getAppointmentTime().isEmpty() || dto.getAppointmentTime().equals(null)){
                throw new IllegalArgumentException("시간이 비어있어요.");
            }else{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                appointmentTime = LocalDateTime.parse(dto.getAppointmentTime(), formatter);
                LocalDateTime now = LocalDateTime.now();
                if(appointmentTime.isBefore(now)){
                    throw new IllegalArgumentException("시간이 과거입니다.");
                }
            }
        }
        postRepository.save(dto.toEntity(author,appointmentTime));
    }

    public List<PostListRes> findAll(){
        return postRepository.findAll().stream().map(Post::postListResFromEntity).collect(Collectors.toList());
    }

    public PostDetailRes findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없는 post 임"));
        return post.postDetailResFromEntity();
    }

    public void updatePost(Long id, PostUpdateReq postUpdateReq){
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없는 post 임"));
        post.updatePost(postUpdateReq);
    }

    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없는 post 임"));
        postRepository.delete(post);
    }

    //    Page<PostListRes>이게 지금 Page로 List<PostListRes>를 감싸놓은 상태.
    public Page<PostListRes> findAllPaging(Pageable pageable){
        Page<Post> postPages = postRepository.findAllByAppointment(pageable,"N");
        return postPages.map(Post::postListResFromEntity);
//        return postRepository.findAll().stream().map(Post::postListResFromEntity).collect(Collectors.toList());
    }

    public List<PostListRes> listFetchJoin(){
//        일반JOIN: author를 join해서 post를 조회하긴 하나, author의 데이터는 실제 참조할 때 쿼리가 N+1발생
//        List<Post> postList = postRepository.findAllJoin(); // 쿼리 1번
//        FETCH JOIN: author를 join해서 조회하고, author의 데이텅도 join시점에서 가져옴. 쿼리 1번발생
        List<Post> postList = postRepository.findAllFetchJoin();
        return postList.stream()
                .map(p->p.postListResFromEntity()).collect(Collectors.toList());//쿼리 n번 발생
    }

}
