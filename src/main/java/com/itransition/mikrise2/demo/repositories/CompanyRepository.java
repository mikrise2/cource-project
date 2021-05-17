package com.itransition.mikrise2.demo.repositories;

import com.itransition.mikrise2.demo.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}
