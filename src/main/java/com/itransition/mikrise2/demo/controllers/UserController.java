package com.itransition.mikrise2.demo.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itransition.mikrise2.demo.Dto.UserCommentDto;
import com.itransition.mikrise2.demo.Dto.UserHeaderDto;
import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import com.itransition.mikrise2.demo.repositories.CompanyRepository;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import com.itransition.mikrise2.demo.services.CloudinaryService;
import com.itransition.mikrise2.demo.services.UserEditingService;
import com.itransition.mikrise2.demo.services.UserTransferService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Controller
public class UserController {
    //TODO
    private final UserRepository userRepository;

    private final UserEditingService userEditingService;

    private final UserTransferService userTransferService;


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
