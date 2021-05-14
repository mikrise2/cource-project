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
import org.springframework.http.ResponseEntity;
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

    private final UserEditingService userEditingService;


    @GetMapping("/{username:^(?!login|logout).+}")
    public String getUserPage(@PathVariable String username, Map<String, Object> model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(username);
        model.put("user", Objects.requireNonNullElseGet(user, User::new));
        return "user";

    }

    @PostMapping("/deleteCompanies")
    public String deleteCompanies(@RequestParam("idsOfSelectedForDelete") String[] ids, @RequestParam("userId") Long id) {
        return "redirect:/" + id;
    }

    @PostMapping("api/user")
    @ResponseBody
    public String getCurrentUser(Principal principal) {
        if (principal != null)
            return userEditingService.getUserRoleByUserName(principal.getName()).toString();
        return "NULL";
    }

}
