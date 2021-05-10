package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.*;
import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import com.itransition.mikrise2.demo.entities.enums.UserRole;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import com.itransition.mikrise2.demo.services.UserEditingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.*;

@Controller
public class LoginAndRegistrationController {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;
    //TODO
    //TODO
    //TODO

    private final UserEditingService userEditingService;

    public LoginAndRegistrationController(UserEditingService userEditingService) {
        this.userEditingService = userEditingService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String acceptRegistration(User userHTML, Map<String, Object> model) {
        userHTML.setActive(true);
//        Bonus bonus = new Bonus();
//        bonus.setCompanyName("myCompany");
//        bonus.setAmountOfMoney(100);
//
        Company company = new Company();
        company.setAmountToCollect(1000L);
        company.setCollectedAmount(10L);
        company.setCompanyType(CompanyType.TECHNOLOGIES);
        company.setInfo("this is my Company");
        company.setName("MyCompany");


        var post = new Post();
        post.setText("Post text");
        Comment comment = new Comment();
        Comment comment1 = new Comment();
        comment.setText("comment text");
        comment1.setText("comment1 text");


        var user21 = userRepository.findById(1L);

        comment.setUser(user21.get());


        post.addComment(comment);
        post.addComment(comment1);
//
//        Company company1 = new Company();
//        company1.setAmountToCollect(10001L);
//        company1.setCollectedAmount(101L);
//        company1.setCompanyType(CompanyType.TECHNOLOGIES);
//        company1.setInfo("this is my Company1");
//        company1.setName("MyCompany1");
//
        Bonus bonus1 = new Bonus();
        bonus1.setPrice(1001);
        bonus1.setInfo("new Bonus");
////        bonusList.add(bonus);
////        bonusList.add(bonus1);
//        userHTML.addBonus(bonus);
//        userHTML.addBonus(bonus1);
        company.addBonus(bonus1);
        bonus1.setCompany(company);

        company.addPost(post);

        companyRepository.save(company);
//        userHTML.addCompany(company);
//        userHTML.addBonus(bonus1);
//        userHTML.addCompany(company1);
//        userHTML.getBonuses().add(bonus);
        userHTML.setUserRole(UserRole.USER);


        if (userEditingService.saveUser(userHTML))
            return "redirect:/login";
        else {
            model.put("error", "User exists!");
            return "registration";
        }
    }


}
