package com.gutorov.university.service;

import com.gutorov.university.api.program.ProgramRq;
import com.gutorov.university.api.program.ProgramRs;
import com.gutorov.university.entity.ProgramEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.ProgramMapper;
import com.gutorov.university.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    public ProgramService(ProgramRepository programRepository,
                          ProgramMapper programMapper) {
        this.programRepository = programRepository;
        this.programMapper = programMapper;
    }

    public ProgramEntity getEntity(UUID id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ProgramEntity.class, id.toString()));
    }

    public List<ProgramRs> getAll() {
        return programMapper.toResponse(programRepository.findAll());
    }

    public ProgramRs get(UUID id) {
        return programMapper.toResponse(getEntity(id));
    }

    public ProgramRs create(ProgramRq request) {
        ProgramEntity saved = programRepository.save(programMapper.toEntity(request));
        return programMapper.toResponse(saved);
    }

    public ProgramRs update(UUID id, ProgramRq request) {
        ProgramEntity entity = getEntity(id);
        entity.setName(request.getName());
        ProgramEntity saved = programRepository.save(entity);
        return programMapper.toResponse(saved);
    }

    public ProgramRs delete(UUID id) {
        ProgramEntity entity = getEntity(id);
        programRepository.delete(entity);
        return programMapper.toResponse(entity);
    }
}