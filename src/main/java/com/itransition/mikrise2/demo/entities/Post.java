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
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @OneToMany
    private List<Comment> comments;

    private Date publicationDate;


}
