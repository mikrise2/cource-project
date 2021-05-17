package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.*;
import com.itransition.mikrise2.demo.entities.enums.UserRole;
import com.itransition.mikrise2.demo.services.CloudinaryService;
import com.itransition.mikrise2.demo.services.UserEditingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class LoginAndRegistrationController {
    private final CloudinaryService cloudinaryService;
    private final UserEditingService userEditingService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String acceptRegistration(User userHTML, @RequestParam("file") MultipartFile file, Map<String, Object> model) {
        userHTML.setActive(true);
        userHTML.setUserRole(UserRole.USER);
        String url = cloudinaryService.uploadFile(file);
        userHTML.setPhotoUrl(url);
        if (userEditingService.saveUser(userHTML))
            return "redirect:/login";
        else {
            model.put("error", "User exists!");
            return "registration";
        }
    }


}
