package com.rods.jobtracking.repository;

import com.rods.jobtracking.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
