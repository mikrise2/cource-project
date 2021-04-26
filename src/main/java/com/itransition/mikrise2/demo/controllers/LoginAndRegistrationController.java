package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.repos.UserRepo;
import com.itransition.mikrise2.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class LoginAndRegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String acceptRegistration(User userHTML) {
        System.out.println(userHTML);
        userService.saveUser(userHTML);
        return "redirect:/login";
    }


}
