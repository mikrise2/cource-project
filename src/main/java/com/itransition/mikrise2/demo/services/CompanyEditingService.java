package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.model.BonusCreatingModel;
import com.itransition.mikrise2.demo.model.CommentCreatingModel;
import com.itransition.mikrise2.demo.model.PostCreatingModel;

public interface CompanyEditingService {
    void deleteCompany(Company company);

    void updateCompany(Company company);

    void saveCompany(Company company);

    Company getCompanyById(Long id);

    void addBonus(BonusCreatingModel bonusCreatingModel);

    void addPost(PostCreatingModel postCreatingModel);

    String getLastPostId(String companyName);

    void addComment(CommentCreatingModel commentCreatingModel);
}
