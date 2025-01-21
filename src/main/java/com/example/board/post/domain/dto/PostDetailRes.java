package com.example.board.post.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//id, title, contents, authorEmail, createdTime, updatedTime
public class PostDetailRes {
    private Long id;
    private String title;
    private String contents;
    private String authorEmail;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
