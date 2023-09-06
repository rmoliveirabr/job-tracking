package com.rods.jobtracking.service.impl;

import com.rods.jobtracking.dto.JobOpportunityDto;
import com.rods.jobtracking.entity.Company;
import com.rods.jobtracking.entity.JobOpportunity;
import com.rods.jobtracking.entity.JobRole;
import com.rods.jobtracking.exception.ResourceNotFoundException;
import com.rods.jobtracking.mapper.JobOpportunityMapper;
import com.rods.jobtracking.repository.CompanyRepository;
import com.rods.jobtracking.repository.JobOpportunityRepository;
import com.rods.jobtracking.repository.JobRoleRepository;
import com.rods.jobtracking.service.JobOpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobOpportunityServiceImpl implements JobOpportunityService {
    private final JobOpportunityRepository jobOpportunityRepository;
    private final CompanyRepository companyRepository;
    private final JobRoleRepository jobRoleRepository;

    private Company getCompanyById(Long id) {
        if (id == null) return null;

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company with id '" + id + "' not found"));
        return company;
    }

    private JobRole getJobRoleById(Long id) {
        if (id == null) return null;

        JobRole role = jobRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job role with id '" + id + "' not found"));;
        return role;
    }

    @Override
    public JobOpportunityDto createJobOpportunity(JobOpportunityDto jobOpportunityDto) {
        Company hiringCompany = (jobOpportunityDto.getHiringCompany() != null ? getCompanyById(jobOpportunityDto.getHiringCompany().getId()) : null);
        Company recruitmentCompany = (jobOpportunityDto.getRecruitmentCompany() != null ? getCompanyById(jobOpportunityDto.getRecruitmentCompany().getId()) : null);
        JobRole role = getJobRoleById(jobOpportunityDto.getRole().getId());

        JobOpportunity jobOpportunity = JobOpportunityMapper.mapToJobOpportunity(
                jobOpportunityDto, hiringCompany, recruitmentCompany, role);
        JobOpportunity savedJobOpportunity = jobOpportunityRepository.save(jobOpportunity);

        return JobOpportunityMapper.mapToJobOpportunityDto(savedJobOpportunity);
    }

    @Override
    public List<JobOpportunityDto> getAllJobOpportunities() {
        List<JobOpportunity> jobOpportunities = jobOpportunityRepository.findAll();
        List<JobOpportunityDto> jobOpportunityDtos = jobOpportunities.stream()
                .map((jobOpportunity) -> JobOpportunityMapper.mapToJobOpportunityDto(jobOpportunity))
                .collect(Collectors.toList());
        return jobOpportunityDtos;
    }

    @Override
    public JobOpportunityDto getJobOpportunityById(Long id) {
        JobOpportunity jobOpportunity = jobOpportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOpportunity with id '" + id + "' not found"));
        return JobOpportunityMapper.mapToJobOpportunityDto(jobOpportunity);
    }

    @Override
    public JobOpportunityDto updateJobOpportunity(Long id, JobOpportunityDto jobOpportunityDto) {
        JobOpportunity jobOpportunity = jobOpportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOpportunity with id '" + id + "' not found"));

        // update relationships if needed
        jobOpportunity.setHiringCompany(getCompanyById(
               jobOpportunityDto.getHiringCompany() == null ? null : jobOpportunityDto.getHiringCompany().getId())
        );

        jobOpportunity.setRecruitmentCompany(
                jobOpportunityDto.getRecruitmentCompany() == null ? null : getCompanyById(jobOpportunityDto.getRecruitmentCompany().getId())
        );

        if (jobOpportunityDto.getRole().getId() != jobOpportunity.getRole().getId())
            jobOpportunity.setRole(getJobRoleById(jobOpportunityDto.getRole().getId()));

        jobOpportunity = JobOpportunityMapper.mapToJobOpportunity(jobOpportunityDto, jobOpportunity);
        JobOpportunity updatedJobOpportunity = jobOpportunityRepository.save(jobOpportunity);
        return JobOpportunityMapper.mapToJobOpportunityDto(updatedJobOpportunity);
    }

    @Override
    public void deleteJobOpportunity(Long id) {
        JobOpportunity jobOpportunity = jobOpportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOpportunity with id '" + id + "' not found"));

        jobOpportunityRepository.deleteById(id);
    }
}
