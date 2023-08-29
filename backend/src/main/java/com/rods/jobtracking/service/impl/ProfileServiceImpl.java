package com.rods.jobtracking.service.impl;

import com.rods.jobtracking.dto.ProfileDto;
import com.rods.jobtracking.dto.ResumeDto;
import com.rods.jobtracking.entity.Profile;
import com.rods.jobtracking.entity.Resume;
import com.rods.jobtracking.exception.ResourceNotFoundException;
import com.rods.jobtracking.mapper.ProfileMapper;
import com.rods.jobtracking.mapper.ResumeMapper;
import com.rods.jobtracking.repository.ProfileRepository;
import com.rods.jobtracking.repository.ResumeRepository;
import com.rods.jobtracking.service.ProfileService;
import com.rods.jobtracking.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public ProfileDto createProfile(ProfileDto profileDto) {
        Profile profile = ProfileMapper.mapToProfile(profileDto);
        Profile savedProfile = profileRepository.save(profile);

        return ProfileMapper.mapToProfileDto(savedProfile);
    }

    @Override
    public List<ProfileDto> getAllProfiles() {

        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map((profile) -> ProfileMapper.mapToProfileDto(profile))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile with id '" + id + "' not found!"));
        return ProfileMapper.mapToProfileDto(profile);
    }

    @Override
    public void deleteProfileBy(Long id) {
        profileRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Profile with id '" + id + "' not found!"));

        profileRepository.deleteById(id);
    }

    @Override
    public ProfileDto updateProfile(Long id, ProfileDto updatedProfileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile with id '" + id + "' not found!"));

        profile = ProfileMapper.mapToProfile(updatedProfileDto, profile);

        Profile updatedProfile = profileRepository.save(profile);
        return ProfileMapper.mapToProfileDto(updatedProfile);
    }
}
