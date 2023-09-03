package com.rods.jobtracking.service;

import com.rods.jobtracking.dto.JobOpportunityDto;

import java.util.List;

public interface JobOpportunityService {
    JobOpportunityDto createJobOpportunity(JobOpportunityDto jobOpportunityDto);
    List<JobOpportunityDto> getAllJobOpportunities();
    JobOpportunityDto getJobOpportunityById(Long id);
    JobOpportunityDto updateJobOpportunity(Long id, JobOpportunityDto jobOpportunityDto);
    void deleteJobOpportunity(Long id);
}
