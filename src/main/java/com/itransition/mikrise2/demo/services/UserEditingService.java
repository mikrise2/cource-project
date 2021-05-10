package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.User;

public interface UserEditingService {

    boolean saveUser(User user);

    boolean updateUser(User user);

    User getUserByUserName(String username);

}
