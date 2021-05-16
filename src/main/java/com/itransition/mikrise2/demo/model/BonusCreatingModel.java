package com.itransition.mikrise2.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BonusCreatingModel {
    private final String name;
    private final String info;
    private final Long price;
    private final String companyName;
}
