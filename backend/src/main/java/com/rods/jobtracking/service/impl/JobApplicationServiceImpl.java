package com.rods.jobtracking.service.impl;

import com.rods.jobtracking.dto.JobApplicationDto;
import com.rods.jobtracking.entity.*;
import com.rods.jobtracking.exception.ResourceNotFoundException;
import com.rods.jobtracking.mapper.JobApplicationMapper;
import com.rods.jobtracking.repository.*;
import com.rods.jobtracking.service.JobApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobOpportunityRepository jobOpportunityRepository;
    private final ResumeRepository resumeRepository;

    private JobOpportunity getJobOpportunityById(Long id) {
        if (id == null) return null;

        JobOpportunity jobOpportunity = jobOpportunityRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job opportunity with id '" + id + "' not found"));
        return jobOpportunity;
    }

    private Resume getResumeById(Long id) {
        if (id == null) return null;

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume with id '" + id + "' not found"));;
        return resume;
    }

    @Override
    public JobApplicationDto createJobApplication(JobApplicationDto jobApplicationDto) {
        JobOpportunity jobOpportunity = getJobOpportunityById(jobApplicationDto.getJobOpportunity().getId());
        Resume resume = getResumeById(jobApplicationDto.getResume().getId());

        JobApplication jobApplication = JobApplicationMapper.mapToJobApplication(
                jobApplicationDto, jobOpportunity, resume);
        JobApplication savedJobApplication = jobApplicationRepository.save(jobApplication);

        return JobApplicationMapper.mapToJobApplicationDto(savedJobApplication);
    }

    @Override
    public List<JobApplicationDto> getAllJobApplications() {
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();
        List<JobApplicationDto> jobApplicationDtos = jobApplications.stream()
                .map((jobApplication) -> JobApplicationMapper.mapToJobApplicationDto(jobApplication))
                .collect(Collectors.toList());
        return jobApplicationDtos;
    }

    @Override
    public JobApplicationDto getJobApplicationById(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application with id '" + id + "' not found"));
        return JobApplicationMapper.mapToJobApplicationDto(jobApplication);
    }

    @Override
    public JobApplicationDto updateJobApplication(Long id, JobApplicationDto jobApplicationDto) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application with id '" + id + "' not found"));

        // update relationships if needed
        if (jobApplicationDto.getJobOpportunity().getId() != jobApplication.getJobOpportunity().getId())
            jobApplication.setJobOpportunity(getJobOpportunityById(jobApplicationDto.getJobOpportunity().getId()));

        if (jobApplicationDto.getResume().getId() != jobApplication.getResume().getId())
            jobApplication.setResume(getResumeById(jobApplicationDto.getResume().getId()));

        jobApplication = JobApplicationMapper.mapToJobApplication(jobApplicationDto, jobApplication);
        JobApplication updatedJobApplication = jobApplicationRepository.save(jobApplication);
        return JobApplicationMapper.mapToJobApplicationDto(updatedJobApplication);
    }

    @Override
    public void deleteJobApplication(Long id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application with id '" + id + "' not found"));

        jobApplicationRepository.deleteById(id);
    }
}
