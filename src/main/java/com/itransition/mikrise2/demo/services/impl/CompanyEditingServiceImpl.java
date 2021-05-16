package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.Bonus;
import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.model.BonusCreatingModel;
import com.itransition.mikrise2.demo.model.PostCreatingModel;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.services.BonusTransferService;
import com.itransition.mikrise2.demo.services.CompanyEditingService;
import com.itransition.mikrise2.demo.services.PostTransferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompanyEditingServiceImpl implements CompanyEditingService {
    private final CompanyRepository companyRepository;

    private final BonusTransferService bonusTransferService;

    private final PostTransferService postTransferService;

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

    @Override
    public void addBonus(BonusCreatingModel bonusCreatingModel) {
        var company = companyRepository.findByName(bonusCreatingModel.getCompanyName());
        var bonus = bonusTransferService.getBonus(bonusCreatingModel, company);
        company.addBonus(bonus);
        companyRepository.save(company);
    }

    @Override
    public void addPost(PostCreatingModel postCreatingModel) {
        var company = companyRepository.findByName(postCreatingModel.getCompanyName());
        var post = postTransferService.getPost(postCreatingModel);
        company.addPost(post);
        companyRepository.save(company);
    }
}
