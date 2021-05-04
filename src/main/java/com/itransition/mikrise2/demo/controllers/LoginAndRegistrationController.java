package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.services.UserEditingService;
import com.itransition.mikrise2.demo.services.impl.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class LoginAndRegistrationController {
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

        if (userEditingService.saveUser(userHTML))
            return "redirect:/login";
        else {
            model.put("error", "User exists!");
            return "registration";
        }
    }


}
