package com.itransition.mikrise2.demo.entities;

import com.itransition.mikrise2.demo.entities.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String country;
    private boolean active;
    private String photoUrl;

    @OneToMany( mappedBy = "user")
    private List<Company> companies;


    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Bonus> bonuses;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void addBonus(Bonus bonus) {
        if (bonuses == null)
            bonuses = new ArrayList<>();
        bonuses.add(bonus);
    }

    public void addCompany(Company company) {
        if (companies == null)
            companies = new ArrayList<>();
        companies.add(company);
    }
}
