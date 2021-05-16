package com.itransition.mikrise2.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PostCreatingModel {
    private final String name;
    private final String text;
    private final String companyName;
}
