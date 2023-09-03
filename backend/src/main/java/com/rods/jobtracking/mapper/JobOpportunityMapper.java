package com.rods.jobtracking.mapper;

import com.rods.jobtracking.dto.JobOpportunityDto;
import com.rods.jobtracking.entity.Company;
import com.rods.jobtracking.entity.JobOpportunity;
import com.rods.jobtracking.entity.JobRole;

public class JobOpportunityMapper {
    public static JobOpportunityDto mapToJobOpportunityDto(JobOpportunity jobOpportunity) {
        return new JobOpportunityDto(
                jobOpportunity.getId(),
                CompanyMapper.mapToCompanyDto(jobOpportunity.getHiringCompany()),
                CompanyMapper.mapToCompanyDto(jobOpportunity.getRecruitmentCompany()),
                JobRoleMapper.mapToJobRoleDto(jobOpportunity.getRole()),
                jobOpportunity.getJobUrl(),
                jobOpportunity.getJobDescription()
        );
    }

    public static JobOpportunity mapToJobOpportunity(JobOpportunityDto jobOpportunityDto,
                                                     Company hiringCompany,
                                                     Company recruitmentCompany,
                                                     JobRole role) {
        JobOpportunity jobOpportunity = new JobOpportunity();
        jobOpportunity.setHiringCompany(hiringCompany);
        jobOpportunity.setRecruitmentCompany(recruitmentCompany);
        jobOpportunity.setRole(role);
        return mapToJobOpportunity(jobOpportunityDto, jobOpportunity);
    }

    public static JobOpportunity mapToJobOpportunity(JobOpportunityDto jobOpportunityDto, JobOpportunity jobOpportunity) {
        jobOpportunity.setJobDescription(jobOpportunityDto.getJobDescription());
        jobOpportunity.setJobUrl(jobOpportunityDto.getJobUrl());
        return jobOpportunity;
    }
}
