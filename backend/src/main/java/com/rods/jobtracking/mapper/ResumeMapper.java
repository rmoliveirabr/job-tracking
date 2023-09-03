package com.rods.jobtracking.mapper;

import com.rods.jobtracking.dto.ProfileDto;
import com.rods.jobtracking.dto.ResumeDto;
import com.rods.jobtracking.entity.Profile;
import com.rods.jobtracking.entity.Resume;

public class ResumeMapper {
    public static ResumeDto mapToResumeDto(Resume resume) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setId(resume.getId());
        resumeDto.setTemplateName(resume.getTemplateName());
        resumeDto.setDescription(resume.getDescription());
        resumeDto.setVersion(resume.getVersion());
        resumeDto.setProfileId(resume.getProfile().getId());

        return resumeDto;
    }

    public static ResumeDto mapToResumeDto(Resume resume, Long profileId) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setId(resume.getId());
        resumeDto.setTemplateName(resume.getTemplateName());
        resumeDto.setDescription(resume.getDescription());
        resumeDto.setVersion(resume.getVersion());
        resumeDto.setProfileId(profileId);

        return resumeDto;
    }

    public static Resume maptoResume(ResumeDto resumeDto, Profile profile) {
        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setTemplateName(resumeDto.getTemplateName());
        resume.setDescription(resumeDto.getDescription());
        resume.setVersion(resumeDto.getVersion());
        resume.setProfile(profile);

        return resume;
    }
}
