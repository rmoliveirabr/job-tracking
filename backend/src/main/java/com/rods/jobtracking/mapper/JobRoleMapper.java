package com.rods.jobtracking.mapper;

import com.rods.jobtracking.dto.JobRoleDto;
import com.rods.jobtracking.entity.JobRole;

public class JobRoleMapper {
    public static JobRoleDto mapToJobRoleDto(JobRole jobRole) {
        return new JobRoleDto(jobRole.getId(), jobRole.getName(), jobRole.getAlternateNames());
    }

    public static JobRole mapToJobRole(JobRoleDto jobRoleDto) {
        JobRole jobRole = new JobRole();
        return mapToJobRole(jobRoleDto, jobRole);
    }

    public static JobRole mapToJobRole(JobRoleDto jobRoleDto, JobRole jobRole) {
        jobRole.setName(jobRoleDto.getName());
        jobRole.setAlternateNames(jobRoleDto.getAlternateNames());
        return jobRole;
    }
}
