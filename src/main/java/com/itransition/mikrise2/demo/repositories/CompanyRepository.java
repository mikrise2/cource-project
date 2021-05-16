package com.itransition.mikrise2.demo.repositories;

import com.itransition.mikrise2.demo.entities.Company;
import com.itransition.mikrise2.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}
