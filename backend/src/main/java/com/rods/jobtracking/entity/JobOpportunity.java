package com.rods.jobtracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_opportunities")
public class JobOpportunity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="hiring_company_id")
    private Company hiringCompany;

    @ManyToOne
    @JoinColumn(name="recruitment_company_id")
    private Company recruitmentCompany;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private JobRole role;

    @Column(name="job_url")
    private String jobUrl;

    @Column(name="job_description")
    private String jobDescription;
}
