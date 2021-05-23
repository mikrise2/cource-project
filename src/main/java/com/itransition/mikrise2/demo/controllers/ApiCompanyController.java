package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.Dto.CompanyDto;
import com.itransition.mikrise2.demo.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ApiCompanyController {

    private final CompanyService companyService;

    @GetMapping("/company/{id}")
    public CompanyDto getCompanyById(@PathVariable final Long id) {
        return companyService.getCompanyById(id);
    }
}
