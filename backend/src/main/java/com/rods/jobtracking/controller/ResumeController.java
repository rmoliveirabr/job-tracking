package com.rods.jobtracking.controller;

import com.rods.jobtracking.dto.ResumeDto;
import com.rods.jobtracking.service.ResumeService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "*")
public class ResumeController {
    private ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto) {
        ResumeDto savedResume = resumeService.createResume(resumeDto);
        return new ResponseEntity(savedResume, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        List<ResumeDto> resumes = resumeService.getAllResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable("id") Long id) {
        ResumeDto resume = resumeService.getResumeById(id);
        return ResponseEntity.ok(resume);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable("id") Long id,
                                                  @RequestBody ResumeDto resumeDto) {
        ResumeDto resume = resumeService.updateResume(id, resumeDto);
        return ResponseEntity.ok(resume);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteResume(@PathVariable("id") Long id) {
        resumeService.deleteResume(id);
        String returnMessage = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"message\": \"resume deleted successfully!\"\n" +
                "}";
        return ResponseEntity.ok(returnMessage);
    }

}
