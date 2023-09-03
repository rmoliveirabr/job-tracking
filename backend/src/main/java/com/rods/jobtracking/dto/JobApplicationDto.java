package com.rods.jobtracking.dto;

import com.rods.jobtracking.entity.JobOpportunity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {
    private Long id;
    private Date date;
    private JobOpportunityDto jobOpportunity;
    private ResumeDto resume;
    private String status;
    private Float probability;
}
