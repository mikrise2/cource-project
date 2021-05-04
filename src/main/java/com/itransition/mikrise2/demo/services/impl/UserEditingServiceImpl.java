package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.repos.UserRepo;
import com.itransition.mikrise2.demo.services.UserEditingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEditingServiceImpl implements UserEditingService {
    private final UserRepo userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserEditingServiceImpl(UserRepo userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
