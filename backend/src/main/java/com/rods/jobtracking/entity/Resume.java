package com.rods.jobtracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="template_name", nullable = false)
    private String templateName;

    @Column
    private String description;

    @Column
    private Integer version;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;
}
