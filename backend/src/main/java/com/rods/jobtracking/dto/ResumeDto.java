package com.rods.jobtracking.dto;

import com.rods.jobtracking.entity.Profile;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResumeDto {
    private Long id;
    private String templateName;
    private String description;
    private Integer version;
    private Long profileId;
}
