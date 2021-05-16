package com.itransition.mikrise2.demo.entities;

import com.itransition.mikrise2.demo.model.BonusCreatingModel;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bonuses")
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String info;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
