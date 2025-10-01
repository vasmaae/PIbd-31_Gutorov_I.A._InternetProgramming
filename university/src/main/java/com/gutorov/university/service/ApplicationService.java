package com.gutorov.university.service;

import com.gutorov.university.api.application.ApplicationRq;
import com.gutorov.university.api.application.ApplicationRs;
import com.gutorov.university.entity.ApplicationEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.ApplicationMapper;
import com.gutorov.university.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {
    Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ProgramService programService;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, ProgramService programService) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.programService = programService;
    }

    public ApplicationEntity getEntity(UUID id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ApplicationEntity.class, id.toString()));
    }

    public List<ApplicationRs> getAll() {
        return applicationMapper.toResponse(applicationRepository.findAll());
    }

    public ApplicationRs get(UUID id) {
        return applicationMapper.toResponse(getEntity(id));
    }

    public ApplicationRs create(ApplicationRq request) {
        final var entity = applicationRepository.save(applicationMapper.toEntity(request));
        return applicationMapper.toResponse(entity);
    }

    public ApplicationRs update(UUID id, ApplicationRq request) {
        logger.info("{}", request.isAdmitted());
        var entity = getEntity(id);
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        entity.setProgram(programService.getEntity(request.getProgramId()));
        entity.setSubmissionDate(request.getSubmissionDate());
        entity.setAdmitted(request.isAdmitted());
        entity = applicationRepository.save(entity);
        return applicationMapper.toResponse(entity);
    }

    public ApplicationRs delete(UUID id) {
        final var entity = getEntity(id);
        applicationRepository.delete(entity);
        return applicationMapper.toResponse(entity);
    }
}