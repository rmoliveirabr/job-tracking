package com.rods.jobtracking.service.impl;

import com.rods.jobtracking.dto.JobRoleDto;
import com.rods.jobtracking.entity.JobRole;
import com.rods.jobtracking.exception.ResourceNotFoundException;
import com.rods.jobtracking.mapper.JobRoleMapper;
import com.rods.jobtracking.repository.JobRoleRepository;
import com.rods.jobtracking.service.JobRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobRoleServiceImpl implements JobRoleService {
    private final JobRoleRepository jobRoleRepository;

    @Override
    public JobRoleDto createJobRole(JobRoleDto jobRoleDto) {
        JobRole jobRole = JobRoleMapper.mapToJobRole(jobRoleDto);
        JobRole savedJobRole = jobRoleRepository.save(jobRole);

        return JobRoleMapper.mapToJobRoleDto(savedJobRole);
    }

    @Override
    public List<JobRoleDto> getAllJobRoles() {
        List<JobRole> jobRoles = jobRoleRepository.findAll();
        List<JobRoleDto> jobRoleDtos = jobRoles.stream()
                .map((jobRole) -> JobRoleMapper.mapToJobRoleDto(jobRole))
                .collect(Collectors.toList());
        return jobRoleDtos;
    }

    @Override
    public JobRoleDto getJobRoleById(Long id) {
        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobRole with id '" + id + "' not found"));
        return JobRoleMapper.mapToJobRoleDto(jobRole);
    }

    // TODO: Issue - didn't update alternate names
    @Override
    public JobRoleDto updateJobRole(Long id, JobRoleDto jobRoleDto) {
        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobRole with id '" + id + "' not found"));

        jobRole = JobRoleMapper.mapToJobRole(jobRoleDto, jobRole);
        JobRole updatedJobRole = jobRoleRepository.save(jobRole);
        return JobRoleMapper.mapToJobRoleDto(updatedJobRole);
    }

    @Override
    public void deleteJobRole(Long id) {
        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobRole with id '" + id + "' not found"));

        jobRoleRepository.deleteById(id);
    }
}
