package com.rods.jobtracking.service;

import com.rods.jobtracking.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto createProfile(ProfileDto profileDto);
    List<ProfileDto> getAllProfiles();
    ProfileDto getProfileById(Long id);
    ProfileDto updateProfile(Long id, ProfileDto profile);
    void deleteProfileBy(Long id);
}
