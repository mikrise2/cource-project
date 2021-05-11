package com.itransition.mikrise2.demo.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import com.itransition.mikrise2.demo.services.CloudinaryService;
import com.itransition.mikrise2.demo.services.UserEditingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    private final CloudinaryService cloudinaryService;


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
    @ResponseBody
    public String createCompany(Principal principal, Company company, @RequestParam("companyTypeString") String companyTypeStr, @RequestParam("finishDateString") String finishDateStr, @RequestParam("file") MultipartFile file) {
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

        String url = cloudinaryService.uploadFile(file);
        company.setPhotoUrl(url);
        companyRepository.save(company);


        var user = userEditingService.getUserByUserName(principal.getName());
        user.addCompany(company);
        userEditingService.updateUser(user);


        System.out.println(company);


        //TODO
        return "main";

    }

}
