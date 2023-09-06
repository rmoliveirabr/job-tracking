package com.rods.jobtracking.controller;

import com.rods.jobtracking.dto.ProfileDto;
import com.rods.jobtracking.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class ProfileController {
    private ProfileService profileService;

    // Add Profile
    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto savedProfileDto = profileService.createProfile(profileDto);
        ResponseEntity<ProfileDto> response = new ResponseEntity<>(savedProfileDto, HttpStatus.CREATED);
        return response;
    }

    // Get Profile By Id
    @GetMapping("{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable("id") Long id) {
        ProfileDto profileDto = profileService.getProfileById(id);
        return ResponseEntity.ok(profileDto);
    }

    // Get All Profiles
    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        List<ProfileDto> profileDtos = profileService.getAllProfiles();
        return ResponseEntity.ok(profileDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable("id") Long id,
                                                    @RequestBody ProfileDto profileDto) {
        ProfileDto updatedProfileDto = profileService.updateProfile(id, profileDto);
        return ResponseEntity.ok(updatedProfileDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfileBy(id);
        String returnMessage = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"message\": \"Profile deleted successfully!\"\n" +
                "}";
        return ResponseEntity.ok(returnMessage);
    }
}
