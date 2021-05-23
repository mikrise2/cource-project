package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.Dto.CompanyDto;
import com.itransition.mikrise2.demo.exception.CompanyNotFoundException;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository companyRepository;

    @Override
    public CompanyDto getCompanyById(final Long id) {
        var company = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFoundException(String.format("Company not found with id %s", id)));
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
