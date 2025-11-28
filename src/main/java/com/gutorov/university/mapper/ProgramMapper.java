package com.gutorov.university.mapper;

import com.gutorov.university.api.program.ProgramRq;
import com.gutorov.university.api.program.ProgramRs;
import com.gutorov.university.entity.ProgramEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class ProgramMapper {
    public ProgramRq toRequest(String name) {
        ProgramRq rq = new ProgramRq();
        rq.setName(name);
        return rq;
    }

    public ProgramRs toResponse(ProgramEntity entity) {
        ProgramRs rs = new ProgramRs();
        rs.setId(entity.getId());
        rs.setName(entity.getName());
        return rs;
    }

    public List<ProgramRs> toResponse(Iterable<ProgramEntity> entities) {
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    public ProgramEntity toEntity(ProgramRq request) {
        return new ProgramEntity(request.getName());
    }
}