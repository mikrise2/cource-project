package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Company;

public interface CompanyEditingService {
    void deleteCompany(Company company);

    void updateCompany(Company company);

    void saveCompany(Company company);

    Company getCompanyById(Long id);
}
