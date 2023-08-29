package com.rods.jobtracking.repository;

import com.rods.jobtracking.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
