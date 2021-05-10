package com.itransition.mikrise2.demo.entities;

import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Bonus> bonuses;
    @Column(name = "dead_line")
    private Date finishDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Post> posts;

    public void addUser(User user) {
        if (users == null)
            users = new ArrayList<>();
        users.add(user);
    }

    public void addBonus(Bonus bonus) {
        if (bonuses == null)
            bonuses = new ArrayList<>();
        bonuses.add(bonus);
    }

    public void addPost(Post post) {
        if (posts == null)
            posts = new ArrayList<>();
        posts.add(post);
    }

}
