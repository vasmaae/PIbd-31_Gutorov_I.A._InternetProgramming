package com.gutorov.university.mapper;

import com.gutorov.university.api.application.ApplicationRq;
import com.gutorov.university.api.application.ApplicationRs;
import com.gutorov.university.entity.ApplicationEntity;
import com.gutorov.university.service.ProgramService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class ApplicationMapper {
    private final ProgramMapper programMapper;
    private final ProgramService programService;

    public ApplicationMapper(ProgramMapper programMapper, ProgramService programService) {
        this.programMapper = programMapper;
        this.programService = programService;
    }

    public ApplicationRq toRequest(String fullName, String email, UUID programId, LocalDateTime dateFormat, boolean isAdmitted) {
        final ApplicationRq applicationRq = new ApplicationRq();
        applicationRq.setFullName(fullName);
        applicationRq.setEmail(email);
        applicationRq.setProgramId(programId);
        applicationRq.setSubmissionDate(dateFormat);
        applicationRq.setAdmitted(isAdmitted);
        return applicationRq;
    }

    public ApplicationRs toResponse(ApplicationEntity entity) {
        final ApplicationRs applicationRs = new ApplicationRs();
        applicationRs.setId(entity.getId());
        applicationRs.setFullName(entity.getFullName());
        applicationRs.setEmail(entity.getEmail());
        applicationRs.setProgram(programMapper.toResponse(entity.getProgram()));
        applicationRs.setSubmissionDate(entity.getSubmissionDate());
        applicationRs.setAdmitted(entity.isAdmitted());
        return applicationRs;
    }

    public List<ApplicationRs> toResponse(Iterable<ApplicationEntity> entities) {
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    public ApplicationEntity toEntity(ApplicationRq request) {
        return new ApplicationEntity(
                request.getFullName(),
                request.getEmail(),
                (!Objects.isNull(request.getSubmissionDate())) ? request.getSubmissionDate() : null,
                request.isAdmitted(),
                programService.getEntity(request.getProgramId()));
    }
}
