package com.rods.jobtracking.repository;

import com.rods.jobtracking.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
