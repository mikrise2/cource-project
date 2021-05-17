package com.itransition.mikrise2.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private LikeDislike like;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private LikeDislike dislike;
    private Date publicationDate;

}
