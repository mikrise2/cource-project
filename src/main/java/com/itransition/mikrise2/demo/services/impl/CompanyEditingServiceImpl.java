package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.services.CompanyEditingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompanyEditingServiceImpl implements CompanyEditingService {
    private final CompanyRepository companyRepository;

    @Override
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void saveCompany(Company company) {
//TODO
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).isPresent() ? companyRepository.findById(id).get() : null;
    }
}
