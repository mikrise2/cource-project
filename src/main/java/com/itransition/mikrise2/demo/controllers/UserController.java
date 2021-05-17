package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.Dto.UserCommentDto;
import com.itransition.mikrise2.demo.Dto.UserHeaderDto;
import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.services.UserEditingService;
import com.itransition.mikrise2.demo.services.UserTransferService;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserEditingService userEditingService;
    private final UserTransferService userTransferService;


    @GetMapping("/{username:^(?!login|logout).+}")
    public String getUserPage(@PathVariable String username, Map<String, Object> model) {
        var user = userEditingService.getUserByUserName(username);
        model.put("user", Objects.requireNonNullElseGet(user, User::new));
        return "user";

    }

    @PostMapping("/deleteCompanies")
    public String deleteCompanies(@RequestParam("userId") Long id) {
        //TODO
        return "redirect:/" + id;
    }

    @GetMapping("api/user")
    @ResponseBody
    public UserHeaderDto getCurrentUserHeader(Principal principal) {
        if (principal != null)
            return userTransferService.getUserHeaderDto(userEditingService.getUserByUserName(principal.getName()));
        return null;
    }

    @GetMapping("api/current-user")
    @ResponseBody
    public UserCommentDto getCurrentUserComment(Principal principal) {
        if (principal != null)
            return userTransferService.getUserCommentDto(userEditingService.getUserByUserName(principal.getName()));
        return null;
    }

    @GetMapping("api/sender-user")
    @ResponseBody
    public UserCommentDto getSender(@RequestParam("userId")String userId) {
        return userTransferService.getUserCommentDto(userEditingService.getUserById(Long.valueOf(userId)));
    }

}
