package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import com.itransition.mikrise2.demo.services.CloudinaryService;
import com.itransition.mikrise2.demo.services.UserEditingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@Controller
public class CompanyPageController {
    //TODO
    private final UserRepository userRepository;


    private final CompanyRepository companyRepository;
    //TODO

    private final UserEditingService userEditingService;

    private final CloudinaryService cloudinaryService;


    @GetMapping("/company-{company}")
    public String getCompanyPage(@PathVariable Company company, Map<String, Object> model) {
//        var company = companyRepository.findById(companyId)
        System.out.println(company);
        model.put("company", company);
        return "company";
    }

    @GetMapping("/creating-company")
    public String getCreatingCompanyPage() {
        return "editCompany";
    }

    @PostMapping("/creating-company")
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
        company.setCollectedAmount(0L);

        System.out.println(principal.getName());
        if (!file.isEmpty()) {
            String url = cloudinaryService.uploadFile(file);
            company.setPhotoUrl(url);
        }


        var user = userEditingService.getUserByUserName(principal.getName());
        user.addCompany(company);
        company.setUser(user);
        companyRepository.save(company);
        userEditingService.updateUser(user);


        System.out.println(company);


        //TODO
        return "editCompany";
    }

    @GetMapping("/company-update-{company}")
    public String getUpdateCompanyPage(@PathVariable Company company, Map<String, Object> model) {
        model.put("company", company);
        model.put("finishDate", new SimpleDateFormat("yyyy-MM-dd").format(company.getFinishDate()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(company.getFinishDate()));
        return "editCompany";
    }
}
