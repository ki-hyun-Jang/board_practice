package com.example.board.author.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// id, name, email
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorListRes {
    private Long id;
    private String name;
    private String email;
}
