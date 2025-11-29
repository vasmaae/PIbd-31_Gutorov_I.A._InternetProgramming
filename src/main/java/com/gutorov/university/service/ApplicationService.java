package com.gutorov.university.service;

import com.gutorov.university.api.application.ApplicationRq;
import com.gutorov.university.api.application.ApplicationRs;
import com.gutorov.university.entity.ApplicationEntity;
import com.gutorov.university.entity.projection.MonthlyApplications;
import com.gutorov.university.entity.projection.OverallStatsProjection;
import com.gutorov.university.entity.projection.ProgramApplicationsStats;
import com.gutorov.university.entity.projection.ProgramPopularity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ProgramService programService;

    public ApplicationService(ApplicationRepository applicationRepository, ProgramService programService) {
        this.applicationRepository = applicationRepository;
        this.programService = programService;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public ApplicationEntity getEntity(UUID id) {
        return applicationRepository.findById(id).orElseThrow(() -> new NotFoundException(ApplicationEntity.class, id.toString()));
    }

    @Transactional(readOnly = true)
    public List<ApplicationRs> getAll() {
        return ApplicationRs.fromEntityList(applicationRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ApplicationRs get(UUID id) {
        return ApplicationRs.fromEntity(getEntity(id));
    }

    @Transactional
    public ApplicationRs create(ApplicationRq request) {
        final var program = programService.getEntity(request.getProgramId());
        var entity = new ApplicationEntity(request.getFullName(), request.getEmail(), request.getSubmissionDate(), request.isAdmitted(), program);
        entity = applicationRepository.save(entity);
        return ApplicationRs.fromEntity(entity);
    }

    @Transactional
    public ApplicationRs update(UUID id, ApplicationRq request) {
        var entity = getEntity(id);
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        entity.changeProgram(programService.getEntity(request.getProgramId()));
        entity.setSubmissionDate(request.getSubmissionDate());
        entity.setAdmitted(request.isAdmitted());
        entity = applicationRepository.save(entity);
        return ApplicationRs.fromEntity(entity);
    }

    @Transactional
    public ApplicationRs delete(UUID id) {
        final var entity = getEntity(id);
        applicationRepository.delete(entity);
        return ApplicationRs.fromEntity(entity);
    }

    @Transactional(readOnly = true)
    public List<ProgramApplicationsStats> getApplicationsStatsByProgram() {
        return applicationRepository.getApplicationsStatsByProgram();
    }

    @Transactional(readOnly = true)
    public List<ProgramPopularity> getTopPopularPrograms(int limit) {
        return applicationRepository.getTopPopularPrograms(limit);
    }

    @Transactional(readOnly = true)
    public List<ProgramPopularity> getTopPopularPrograms() {
        return applicationRepository.getTopPopularPrograms(5); // значение по умолчанию
    }

    @Transactional(readOnly = true)
    public List<MonthlyApplications> getApplicationsByMonth(LocalDateTime from, LocalDateTime to) {
        return applicationRepository.getApplicationsByMonth(from, to);
    }

    @Transactional(readOnly = true)
    public OverallStatsProjection getOverallStats(LocalDateTime from, LocalDateTime to) {
        return applicationRepository.getOverallStats(from, to);
    }

    @Transactional(readOnly = true)
    public List<ApplicationEntity> getApplicationsByProgramAndPeriod(UUID programId, LocalDateTime from,
                                                                     LocalDateTime to, Boolean isAdmitted) {
        final var program = programService.getEntity(programId);
        return applicationRepository.findByProgramAndSubmissionDateBetweenAndIsAdmitted(program, from, to, isAdmitted);
    }
}