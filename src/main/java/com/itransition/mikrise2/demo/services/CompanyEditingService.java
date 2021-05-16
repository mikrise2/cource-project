package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.model.BonusCreatingModel;

public interface CompanyEditingService {
    void deleteCompany(Company company);

    void updateCompany(Company company);

    void saveCompany(Company company);

    Company getCompanyById(Long id);

    void addBonus(BonusCreatingModel bonusCreatingModel);
}
