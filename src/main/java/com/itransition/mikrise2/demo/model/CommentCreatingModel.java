package com.itransition.mikrise2.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentCreatingModel {
    private final String text;
    private final Long userId;
    private final Long companyId;
    private final Long postId;
}
