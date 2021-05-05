package com.itransition.mikrise2.demo.entities;

import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "amount")
    private Long amountToCollect;
    @Column(name = "collected")
    private Long collectedAmount;
    private String info;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CompanyType companyType;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_company", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public void addUser(User user) {
        if (users == null)
            users = new ArrayList<>();
        users.add(user);
    }

}
