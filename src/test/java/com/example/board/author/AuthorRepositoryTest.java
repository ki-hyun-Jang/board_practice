package com.example.board.author;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.author.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // test 완료 후 데이터가 실제 insert가 되지 않고, 롤백
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest(){
//        테스트 검증 로직: 객체 생성 -> save -> 재조회 -> 객체와 조회한 값이 같은지를 비교
//        준비(prepare, given)
        Author author = Author.builder()
                .name("abc")
                .email("abcd12x31d2@naver.com")
                .password("1234")
                .role(Role.USER)
                .build();

//        실행(execute, when)
        authorRepository.save(author);

//        검증(then)
        Author authorDb = authorRepository.findByEmail("abcd12x31d2@naver.com").orElse(null);
        Assertions.assertEquals(author.getEmail(),authorDb.getEmail());
    }

}
