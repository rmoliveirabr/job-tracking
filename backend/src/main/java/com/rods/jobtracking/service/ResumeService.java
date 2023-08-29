package com.rods.jobtracking.service;

import com.rods.jobtracking.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    ResumeDto createResume(ResumeDto resumeDto);
    List<ResumeDto> getAllResumes();
    ResumeDto getResumeById(Long id);
    ResumeDto updateResume(Long id, ResumeDto resumeDto);
    void deleteResume(Long id);
}
