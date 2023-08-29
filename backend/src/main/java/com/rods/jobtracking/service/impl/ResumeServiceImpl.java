package com.rods.jobtracking.service.impl;

import com.rods.jobtracking.dto.ResumeDto;
import com.rods.jobtracking.entity.Resume;
import com.rods.jobtracking.exception.ResourceNotFoundException;
import com.rods.jobtracking.mapper.ResumeMapper;
import com.rods.jobtracking.repository.ResumeRepository;
import com.rods.jobtracking.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;

    @Override
    public ResumeDto createResume(ResumeDto resumeDto) {
        Resume resume = ResumeMapper.maptoResume(resumeDto, null);
        Resume savedResume = resumeRepository.save(resume);

        return ResumeMapper.mapToResumeDto(savedResume, null);
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumeList = resumeRepository.findAll();

        return resumeList.stream().map((resume) -> ResumeMapper.mapToResumeDto(resume, null))
                .collect(Collectors.toList());
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume with id '" + id + "' not found!"));

        return ResumeMapper.mapToResumeDto(resume, null);
    }

    @Override
    public ResumeDto updateResume(Long id, ResumeDto updatedResumeDto) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume with id '" + id + "' not found!"));

        resume.setTemplateName(updatedResumeDto.getTemplateName());
        resume.setDescription(updatedResumeDto.getDescription());
        resume.setVersion(updatedResumeDto.getVersion());
        Resume updatedResume = resumeRepository.save(resume);

        return ResumeMapper.mapToResumeDto(updatedResume, null);
    }

    @Override
    public void deleteResume(Long id) {
        resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume with id '" + id + "' not found!"));

        resumeRepository.deleteById(id);
    }
}
