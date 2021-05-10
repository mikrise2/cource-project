package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.repositories.UserRepository;
import com.itransition.mikrise2.demo.services.UserEditingService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserEditingServiceImpl implements UserEditingService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public boolean saveUser(User user) {
        User userFromDBByUsername = userRepository.findByUsername(user.getUsername());

        User userFromDBByEmail = userRepository.findByEmail(user.getEmail());

        if (userFromDBByUsername != null && userFromDBByEmail != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userRepository.save(user);
        return false;
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
