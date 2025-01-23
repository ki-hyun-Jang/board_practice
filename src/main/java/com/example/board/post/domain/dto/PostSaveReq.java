package com.example.board.post.domain.dto;

import com.example.board.author.domain.Author;
import com.example.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostSaveReq {
//    title, contents, email
    @NotEmpty
    private String title;
    private String contents;
    @NotEmpty
    private String email;
    private String appointment;
    private String appointmentTime;

    public Post toEntity(Author author, LocalDateTime localDateTime ){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .appointment(this.appointment)
                .appointmentTime(localDateTime)
                .build();
    }
}
