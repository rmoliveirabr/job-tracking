package com.rods.jobtracking.mapper;

import com.rods.jobtracking.dto.JobApplicationDto;
import com.rods.jobtracking.entity.*;

public class JobApplicationMapper {
    public static JobApplicationDto mapToJobApplicationDto(JobApplication jobApplication) {
        return new JobApplicationDto(
                jobApplication.getId(),
                jobApplication.getDate(),
                JobOpportunityMapper.mapToJobOpportunityDto(jobApplication.getJobOpportunity()),
                ResumeMapper.mapToResumeDto(jobApplication.getResume()),
                jobApplication.getStatus().getValue(),
                jobApplication.getProbability()
        );
    }

    public static JobApplication mapToJobApplication(JobApplicationDto jobApplicationDto,
                                                     JobOpportunity jobOpportunity,
                                                     Resume resume) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobOpportunity(jobOpportunity);
        jobApplication.setResume(resume);
        return mapToJobApplication(jobApplicationDto, jobApplication);
    }

    public static JobApplication mapToJobApplication(JobApplicationDto jobApplicationDto, JobApplication jobApplication) {
        jobApplication.setDate(jobApplicationDto.getDate());
        jobApplication.setStatus(JobApplicationStatus.valueOf(jobApplicationDto.getStatus()));
        jobApplication.setProbability(jobApplicationDto.getProbability());
        return jobApplication;
    }
}
