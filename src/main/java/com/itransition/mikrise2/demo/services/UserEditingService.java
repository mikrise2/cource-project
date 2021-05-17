package com.itransition.mikrise2.demo.services;

import com.itransition.mikrise2.demo.entities.User;
import com.itransition.mikrise2.demo.entities.enums.UserRole;
import org.hibernate.usertype.UserType;

public interface UserEditingService {

    boolean saveUser(User user);

    void updateUser(User user);

    User getUserByUserName(String username);

    void deleteUser(User user);

    UserRole getUserRoleByUserName(String username);

    User getUserById(Long id);

}
