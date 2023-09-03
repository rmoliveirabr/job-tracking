package com.rods.jobtracking.service;

import com.rods.jobtracking.dto.JobApplicationDto;

import java.util.List;

public interface JobApplicationService {
    JobApplicationDto createJobApplication(JobApplicationDto jobApplicationDto);
    List<JobApplicationDto> getAllJobApplications();
    JobApplicationDto getJobApplicationById(Long id);
    JobApplicationDto updateJobApplication(Long id, JobApplicationDto jobApplicationDto);
    void deleteJobApplication(Long id);
}
