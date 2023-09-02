package com.rods.jobtracking.controller;

import com.rods.jobtracking.dto.JobRoleDto;
import com.rods.jobtracking.service.JobRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/job-roles")
@CrossOrigin(origins = "*")
public class JobRoleController {
    private JobRoleService jobRoleService;

    @PostMapping
    public ResponseEntity<JobRoleDto> createJobRole(@RequestBody JobRoleDto jobRoleDto) {
        JobRoleDto createdJobRole = jobRoleService.createJobRole(jobRoleDto);
        return new ResponseEntity<>(createdJobRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobRoleDto>> getAllJobRoles() {
        List<JobRoleDto> jobRoles = jobRoleService.getAllJobRoles();
        return ResponseEntity.ok(jobRoles);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobRoleDto> getJobRoleById(@PathVariable("id") Long id) {
        JobRoleDto jobRole = jobRoleService.getJobRoleById(id);
        return ResponseEntity.ok(jobRole);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobRoleDto> updateJobRole(@PathVariable("id") Long id,
                                                    @RequestBody JobRoleDto jobRoleDto) {
        JobRoleDto jobRole = jobRoleService.updateJobRole(id, jobRoleDto);
        return ResponseEntity.ok(jobRole);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobRole(@PathVariable("id") Long id) {
        jobRoleService.deleteJobRole(id);
        return ResponseEntity.ok("JobRole deleted successfully!");
    }
}
