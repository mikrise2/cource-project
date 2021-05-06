package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.Objects;

@Controller
public class UserPageController {
    //TODO
    @Autowired
    private UserRepository userRepository;
    //TODO

    @GetMapping("/{username:^(?!login).+}")
    public String getUserPage(@PathVariable String username, Map<String, Object> model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(username);
        model.put("user", Objects.requireNonNullElseGet(user, User::new));
        return "user";

    }

}
