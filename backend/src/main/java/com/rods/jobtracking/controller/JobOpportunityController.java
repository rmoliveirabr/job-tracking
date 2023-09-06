package com.rods.jobtracking.controller;

import com.rods.jobtracking.dto.JobOpportunityDto;
import com.rods.jobtracking.service.JobOpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/job-opportunities")
@CrossOrigin(origins = "*")
public class JobOpportunityController {
    private JobOpportunityService jobOpportunityService;

    @PostMapping
    public ResponseEntity<JobOpportunityDto> createJobOpportunity(@RequestBody JobOpportunityDto jobOpportunityDto) {
        JobOpportunityDto createdJobOpportunity = jobOpportunityService.createJobOpportunity(jobOpportunityDto);
        return new ResponseEntity<>(createdJobOpportunity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobOpportunityDto>> getAllJobOpportunities() {
        List<JobOpportunityDto> jobOpportunities = jobOpportunityService.getAllJobOpportunities();
        return ResponseEntity.ok(jobOpportunities);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobOpportunityDto> getJobOpportunityById(@PathVariable("id") Long id) {
        JobOpportunityDto jobOpportunity = jobOpportunityService.getJobOpportunityById(id);
        return ResponseEntity.ok(jobOpportunity);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobOpportunityDto> updateJobOpportunity(@PathVariable("id") Long id,
                                                    @RequestBody JobOpportunityDto jobOpportunityDto) {
        JobOpportunityDto jobOpportunity = jobOpportunityService.updateJobOpportunity(id, jobOpportunityDto);
        return ResponseEntity.ok(jobOpportunity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobOpportunity(@PathVariable("id") Long id) {
        jobOpportunityService.deleteJobOpportunity(id);
        String returnMessage = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"message\": \"Job opportunity deleted successfully!\"\n" +
                "}";
        return ResponseEntity.ok(returnMessage);
    }
}
