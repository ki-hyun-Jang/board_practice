package com.example.board.author.domain.dto;

import com.example.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// id, name, email, password, role, postCount, createdTime
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorDetailRes {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private int postCount;
    private LocalDateTime createdTime;

}
