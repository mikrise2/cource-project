package com.itransition.mikrise2.demo.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompanyType {
    TECHNOLOGIES("Technologies"), SPORT("Sport"), EDUCATION("Education");

    private final String value;

}
