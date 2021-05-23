package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.model.BonusCreatingModel;
import com.itransition.mikrise2.demo.model.CommentCreatingModel;
import com.itransition.mikrise2.demo.model.PostCreatingModel;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@Service
public class CompanyEditingServiceImpl implements CompanyEditingService {
    private final CompanyRepository companyRepository;
    private final BonusTransferService bonusTransferService;
    private final PostTransferService postTransferService;
    private final CommentTransferService commentTransferService;
    private final UserEditingService userEditingService;

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
        if (Objects.isNull(company.getBonuses())) {
            company.setBonuses(new ArrayList<>());
        }
        company.getBonuses().add(bonus);
        companyRepository.save(company);
    }

    @Override
    public void addPost(PostCreatingModel postCreatingModel) {
        var company = companyRepository.findByName(postCreatingModel.getCompanyName());
        var post = postTransferService.getPost(postCreatingModel);
        if (company.getPosts() == null)
            company.setPosts(new ArrayList<>());
        company.getPosts().add(post);
        companyRepository.save(company);
    }

    @Override
    public String getLastPostId(String companyName) {
        var posts = companyRepository.findByName(companyName).getPosts();
        return posts.get(posts.size() - 1).getId().toString();
    }

    @Override
    public void addComment(CommentCreatingModel commentCreatingModel) {
        var company = companyRepository.findById(commentCreatingModel.getCompanyId()).get();
        var post = company.getPosts().stream().filter(it -> it.getId().equals(commentCreatingModel.getPostId())).findAny().get();
        var comment = commentTransferService.getComment(commentCreatingModel);
        comment.setUser(userEditingService.getUserById(commentCreatingModel.getUserId()));
        if (post.getComments() == null)
            post.setComments(new ArrayList<>());
        post.getComments().add(comment);
        companyRepository.save(company);
    }
}
