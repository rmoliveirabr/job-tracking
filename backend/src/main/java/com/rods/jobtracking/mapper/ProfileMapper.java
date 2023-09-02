package com.rods.jobtracking.mapper;

import com.rods.jobtracking.dto.ProfileDto;
import com.rods.jobtracking.dto.ResumeDto;
import com.rods.jobtracking.entity.Profile;
import com.rods.jobtracking.entity.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProfileMapper {
    public static ProfileDto mapToProfileDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto(profile.getId(), profile.getName(), null);

        List<ResumeDto> resumeDtos = new ArrayList<>();
        for (Resume resume: profile.getResumes()) {
            resumeDtos.add(ResumeMapper.mapToResumeDto(resume, profile.getId()));
        }
        profileDto.setResumes(resumeDtos);

        return profileDto;
    }

    public static Profile mapToProfile(ProfileDto profileDto, Profile profile) {
        profile.setName(profileDto.getName());

        // update resumes to keep, remove resumes to remove
        List<Resume> resumesToRemove = new ArrayList<>();
        profile.getResumes().forEach((resume) -> {
            Optional<ResumeDto> foundDtoOptional = profileDto.getResumes().stream()
                    .filter((resumeDto) -> resumeDto.getTemplateName().equals(resume.getTemplateName()))
                    .findFirst();

            if (foundDtoOptional.isPresent()) {
                ResumeDto foundDto = foundDtoOptional.get();
                resume.setDescription(foundDto.getDescription());
                resume.setVersion(foundDto.getVersion());
            } else {
                resume.setProfile(null);
                resumesToRemove.add(resume);
            }
        });
        profile.getResumes().removeAll(resumesToRemove);

        // get resumes to add (resumes in the Dto list not found in object)
        List<Resume> resumesToAdd = profileDto.getResumes().stream()
                .map(resumeDto -> ResumeMapper.maptoResume(resumeDto, profile))
                .filter(resumeFromDto -> profile.getResumes().stream()
                        .noneMatch(resume -> resumeFromDto.getTemplateName().equals(resume.getTemplateName())))
                .collect(Collectors.toList());
        profile.getResumes().addAll(resumesToAdd);

        return profile;
    }

    public static Profile mapToProfile(ProfileDto profileDto) {
        Profile profile = new Profile(profileDto.getId(), profileDto.getName(), null);

        List<Resume> resumes = new ArrayList<>();
        for (ResumeDto resumeDto: profileDto.getResumes()) {
            resumes.add(ResumeMapper.maptoResume(resumeDto, profile));
        }

        return profile;
    }

}
