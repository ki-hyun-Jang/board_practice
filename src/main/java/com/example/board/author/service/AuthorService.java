package com.example.board.author.service;

import com.example.board.author.domain.dto.AuthorDetailRes;
import com.example.board.author.domain.dto.AuthorUpdateReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.author.domain.Author;
import com.example.board.author.domain.dto.AuthorListRes;
import com.example.board.author.domain.dto.AuthorSaveReq;
import com.example.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
    }


    public void save(AuthorSaveReq dto){
        if (authorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        authorRepository.save(dto.toEntity());
//        Author author = authorRepository.save(dto.toEntity());
//        cascade를 활용하지 않고, 별도로 post 데이터를 만드는 경우
//        postRepository.save(Post.builder()
//                .title("반갑습니다.")
//                .contents("처음 뵙겠습니다.")
//                .author(author)
//                .build());

//        cascade를 활용해서 post데이터를 함께 만드는 경우
//        Author author = Author.builder()
//                .name(dto.getName())
//                .email(dto.getEmail())
//                .role(dto.getRole())
//                .password(dto.getPassword())
//                .build();
////        post를 생성하는 시점에 author가 아직 DB에 저장되지 않은 것으로 보여지나, jpa가 author와 post를 save하는 시점에 선후관계를 맞춰줌.
//        author.getPosts().add(Post.builder().title("반가워요1.").author(author).build());
//        author.getPosts().add(Post.builder().title("반가워요2.").author(author).build());
//        authorRepository.save(author);
    }

    public List<AuthorListRes> findAll(){
        return authorRepository.findAll().stream().map(Author::authorListResFromEntity).collect(Collectors.toList());
    }

    public void deleteById(Long id){
        if (authorRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("없는 아이디입니다.");
        }
        authorRepository.deleteById(id);
    }

    public AuthorDetailRes authorDetail(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없는 아이디입니다."));
        return author.authorDetailFromEntity();
    }

        public void updateAuthor(Long id, AuthorUpdateReq authorUpdateReq){
            Author author = authorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없는 아이디입니다,"));
            author.updateNameAndPassword(authorUpdateReq);
//            기존객체에 변경이 발생할 경우, 별도의 save가 없이도 jpa가 엔티티의 변경을 인지하고, 변경사항을 db에 반영
//            이를 dirtychecking이라 부르고, 반드시 transactional어노테이션이 있을 경우 동작.
    }

}
