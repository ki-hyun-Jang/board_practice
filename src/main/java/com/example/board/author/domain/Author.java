package com.example.board.author.domain;

import com.example.board.author.domain.dto.AuthorListRes;
import com.example.board.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(length = 20, nullable = false)
    private String name ;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

//    enum은 기본적으로 숫자값으로 db에 들어감으로, 별도로 STRING 지정 필요.
    @Enumerated(EnumType.STRING)
    private Role role;

    public Author(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public AuthorListRes authorListResFromEntity(){
        return AuthorListRes.builder().name(this.name).email(this.email).id(this.id).build();
    }
}
