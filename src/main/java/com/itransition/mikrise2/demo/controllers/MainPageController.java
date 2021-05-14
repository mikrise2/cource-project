package com.itransition.mikrise2.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String redirectToMainPage() {
        return "redirect:/feed";
    }


}
