package com.itransition.mikrise2.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCommentDto {
    private final Long id;
    private final String username;
    private final String photoUrl;
}
