package com.example.board.post.domain.dto;

import com.example.board.author.domain.Author;
import com.example.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

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

    public Post toEntity(Author author){
        return Post.builder().title(this.title).contents(this.contents).author(author).build();
    }
}
