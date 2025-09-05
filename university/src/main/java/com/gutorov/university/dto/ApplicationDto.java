package com.gutorov.university.dto;

import java.time.LocalDateTime;

public class ApplicationDto {
    private String id;
    private String fullName;
    private String email;
    private String programId;
    private ProgramDto program;
    private LocalDateTime submissionDate;
    private boolean isAdmitted;

    public ApplicationDto() {
    }

    public ApplicationDto(String id, String fullName, String email, String programId, LocalDateTime dateFormat, boolean isAdmitted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.programId = programId;
        this.submissionDate = dateFormat;
        this.isAdmitted = isAdmitted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public ProgramDto getProgram() {
        return program;
    }

    public void setProgram(ProgramDto program) {
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

    public void setAdmitted(boolean admitted) {
        this.isAdmitted = admitted;
    }

    @Override
    public String toString() {
        return "ApplicationDto{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", programId='" + programId + '\'' +
                ", program=" + program +
                ", submissionDate=" + submissionDate +
                ", isAdmitted=" + isAdmitted +
                '}';
    }
}
