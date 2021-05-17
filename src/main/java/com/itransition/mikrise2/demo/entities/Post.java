package com.itransition.mikrise2.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String text;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Comment> comments;
    private Date publicationDate;
}
