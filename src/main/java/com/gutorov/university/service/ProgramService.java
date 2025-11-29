package com.gutorov.university.service;

import com.gutorov.university.api.program.ProgramRq;
import com.gutorov.university.api.program.ProgramRs;
import com.gutorov.university.entity.ProgramEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public ProgramEntity getEntity(UUID id) {
        return programRepository.findById(id).orElseThrow(() -> new NotFoundException(ProgramEntity.class, id.toString()));
    }

    @Transactional(readOnly = true)
    public List<ProgramRs> getAll() {
        return ProgramRs.fromEntityList(programRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ProgramRs get(UUID id) {
        return ProgramRs.fromEntity(getEntity(id));
    }

    @Transactional
    public ProgramRs create(ProgramRq request) {
        var entity = new ProgramEntity(request.getName());
        entity = programRepository.save(entity);
        return ProgramRs.fromEntity(entity);
    }

    @Transactional
    public ProgramRs update(UUID id, ProgramRq request) {
        var entity = getEntity(id);
        entity.setName(request.getName());
        entity = programRepository.save(entity);
        return ProgramRs.fromEntity(entity);
    }

    @Transactional
    public ProgramRs delete(UUID id) {
        final var entity = getEntity(id);
        programRepository.delete(entity);
        return ProgramRs.fromEntity(entity);
    }
}