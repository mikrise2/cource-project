package com.itransition.mikrise2.demo.services.impl;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.entities.enums.UserRole;
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
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserRole getUserRoleByUserName(String username) {
        return userRepository.findByUsername(username).getUserRole();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
