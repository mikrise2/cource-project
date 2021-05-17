package com.itransition.mikrise2.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LikeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quality;
    @ManyToMany
    private List<User> users;


    public void addUser(User user) {
        if (users == null)
            users = new ArrayList<>();
        users.add(user);
    }

}
