package com.rods.jobtracking.dto;

import com.rods.jobtracking.entity.JobRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpportunityDto {
    private Long id;
    private CompanyDto hiringCompany;
    private CompanyDto recruitmentCompany;
    private JobRoleDto role;
    private String jobUrl;
    private String jobDescription;
}
