package com.rods.jobtracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="job_opportunity_id")
    private JobOpportunity jobOpportunity;

    @ManyToOne
    @JoinColumn(name="resume_id")
    private Resume resume;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private JobApplicationStatus status;

    @Column(name="probability")
    private Float probability;
}
