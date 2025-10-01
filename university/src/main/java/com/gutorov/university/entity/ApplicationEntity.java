package com.gutorov.university.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ApplicationEntity extends BaseEntity {
    private String fullName;
    private String email;
    private UUID programId;
    private ProgramEntity program;
    private LocalDateTime submissionDate;
    private boolean isAdmitted;

    public ApplicationEntity() {
        super();
    }

    public ApplicationEntity(String fullName, String email, UUID programId, LocalDateTime submissionDate, boolean isAdmitted) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.programId = programId;
        this.submissionDate = submissionDate;
        this.isAdmitted = isAdmitted;
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

    public ProgramEntity getProgram() {
        return program;
    }

    public void setProgram(ProgramEntity program) {
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

    @Override
    public String toString() {
        return "ApplicationEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", programId=" + programId +
                ", program=" + program +
                ", submissionDate=" + submissionDate +
                ", isAdmitted=" + isAdmitted +
                '}';
    }
}
