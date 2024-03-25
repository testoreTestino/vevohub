package com.vevohub.integrator.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@ToString
public class CandidatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String osca;
    private String recruiter;
    private String fullNameCandidate;
    private String profile;
    private String locationCity;
    private String financialExpectations;
    private String contactNo;
    private String email;
    private String linkedinLink;
    private String interviewRtWeek;
    private String feedback;
    private String status;
    private String details;
    private Boolean isChecked;
}