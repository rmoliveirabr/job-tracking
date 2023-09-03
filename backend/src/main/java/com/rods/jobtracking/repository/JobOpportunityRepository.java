package com.rods.jobtracking.repository;

import com.rods.jobtracking.entity.JobOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunity, Long> {
}
