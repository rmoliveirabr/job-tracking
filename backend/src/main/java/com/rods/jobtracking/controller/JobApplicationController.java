package com.rods.jobtracking.controller;

import com.rods.jobtracking.dto.JobApplicationDto;
import com.rods.jobtracking.service.JobApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/job-applications")
@CrossOrigin(origins = "*")
public class JobApplicationController {
    private JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<JobApplicationDto> createJobApplication(@RequestBody JobApplicationDto jobApplicationDto) {
        JobApplicationDto createdJobApplication = jobApplicationService.createJobApplication(jobApplicationDto);
        return new ResponseEntity<>(createdJobApplication, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplications() {
        List<JobApplicationDto> jobApplications = jobApplicationService.getAllJobApplications();
        return ResponseEntity.ok(jobApplications);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable("id") Long id) {
        JobApplicationDto jobApplication = jobApplicationService.getJobApplicationById(id);
        return ResponseEntity.ok(jobApplication);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobApplicationDto> updateJobApplication(@PathVariable("id") Long id,
                                                    @RequestBody JobApplicationDto jobApplicationDto) {
        JobApplicationDto jobApplication = jobApplicationService.updateJobApplication(id, jobApplicationDto);
        return ResponseEntity.ok(jobApplication);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobApplication(@PathVariable("id") Long id) {
        jobApplicationService.deleteJobApplication(id);
        String returnMessage = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"message\": \"Job application deleted successfully!\"\n" +
                "}";
        return ResponseEntity.ok(returnMessage);
    }
}
