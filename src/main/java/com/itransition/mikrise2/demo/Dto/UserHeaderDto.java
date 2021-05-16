package com.itransition.mikrise2.demo.Dto;

import com.itransition.mikrise2.demo.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserHeaderDto {
    private final Long id;
    private final UserRole userRole;
}
