package com.gutorov.university.api.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class ApplicationRq {
    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotNull
    private UUID programId;
    private LocalDateTime submissionDate;
    @JsonProperty("isAdmitted")
    private boolean isAdmitted;

    public ApplicationRq() {
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

    public UUID getProgramId() {
        return programId;
    }

    public void setProgramId(UUID programId) {
        this.programId = programId;
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
