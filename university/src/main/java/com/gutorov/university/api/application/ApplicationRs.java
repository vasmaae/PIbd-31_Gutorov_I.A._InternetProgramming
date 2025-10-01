package com.gutorov.university.api.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gutorov.university.api.program.ProgramRs;
import com.gutorov.university.entity.ProgramEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ApplicationRs {
    private UUID id;
    private String fullName;
    private String email;
    private ProgramRs program;
    private LocalDateTime submissionDate;
    @JsonProperty("isAdmitted")
    private boolean isAdmitted;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProgramRs getProgram() {
        return program;
    }

    public void setProgram(ProgramRs program) {
        this.program = program;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public boolean isAdmitted() {
        return isAdmitted;
    }

    public void setAdmitted(boolean isAdmitted) {
        this.isAdmitted = isAdmitted;
    }
}
