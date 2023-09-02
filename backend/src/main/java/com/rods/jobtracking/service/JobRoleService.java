package com.rods.jobtracking.service;

import com.rods.jobtracking.dto.JobRoleDto;

import java.util.List;

public interface JobRoleService {
    JobRoleDto createJobRole(JobRoleDto jobRoleDto);
    List<JobRoleDto> getAllJobRoles();
    JobRoleDto getJobRoleById(Long id);
    JobRoleDto updateJobRole(Long id, JobRoleDto jobRoleDto);
    void deleteJobRole(Long id);
}
