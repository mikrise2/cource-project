package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import com.itransition.mikrise2.demo.services.UserEditingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Controller
public class UserPageController {
    //TODO
    private final UserRepository userRepository;


    private final CompanyRepository companyRepository;
    //TODO

    private final UserEditingService userEditingService;


    @GetMapping("/{username:^(?!login).+}")
    public String getUserPage(@PathVariable String username, Map<String, Object> model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(username);
        model.put("user", Objects.requireNonNullElseGet(user, User::new));
        return "user";

    }

    @GetMapping("/creating-company")
    public String getCreatingCompanyPage() {
        return "createCompany";
    }

    @PostMapping("/creating-company")
    public String createCompany(Principal principal, Company company, @RequestParam("companyTypeString") String companyTypeStr, @RequestParam("finishDateString") String finishDateStr) {
        System.out.println(finishDateStr);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(finishDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        company.setFinishDate(date);
        company.setCompanyType(CompanyType.valueOf(companyTypeStr.toUpperCase()));


        System.out.println(principal.getName());

        var user = userEditingService.getUserByUserName(principal.getName());
        user.addCompany(company);
        companyRepository.save(company);
        userEditingService.updateUser(user);


        System.out.println(company);


        //TODO
        return "main";

    }

}
