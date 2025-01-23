package com.example.board.post.domain;

import com.example.board.author.domain.Author;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.domain.dto.PostDetailRes;
import com.example.board.post.domain.dto.PostListRes;
import com.example.board.post.domain.dto.PostUpdateReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000)
    private String contents;

    private String appointment;
    private LocalDateTime appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;

    public PostListRes postListResFromEntity(){
        return PostListRes.builder()
                .id(this.id)
                .title(this.title)
                .authorEmail(this.author.getEmail())
                .build();
    }

//    id, title, contents, authorEmail, createdTime, updatedTime
    public PostDetailRes postDetailResFromEntity(){
        return PostDetailRes.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .authorEmail(this.author.getEmail())
                .createdTime(this.getCreatedTime())
                .updatedTime(this.getUpdateTime())
                .build();
    }

    public void updatePost(PostUpdateReq postUpdateReq){
        this.title = postUpdateReq.getTitle();
        this.contents = postUpdateReq.getContents();
    }

    public void updateAppointment(String appointment){
        this.appointment = appointment;
    }
}
