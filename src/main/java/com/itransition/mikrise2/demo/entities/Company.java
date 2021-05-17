package com.itransition.mikrise2.demo.entities;

import com.itransition.mikrise2.demo.entities.enums.CompanyType;
import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne
    private User user;
    @OneToMany(cascade = {CascadeType.MERGE})
    private List<Bonus> bonuses;
    private Date finishDate;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Post> posts;
    @ElementCollection
    private List<String> photoUrls;
    private String tags;
    private String photoUrl;
    private String videoUrl;
}
