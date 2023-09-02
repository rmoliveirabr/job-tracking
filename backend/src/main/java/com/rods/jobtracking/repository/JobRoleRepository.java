package com.rods.jobtracking.repository;

import com.rods.jobtracking.entity.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
}
