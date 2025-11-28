package com.gutorov.university.mapper;

import com.gutorov.university.api.author.AuthorRq;
import com.gutorov.university.api.author.AuthorRs;
import com.gutorov.university.entity.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class AuthorMapper {
    public AuthorRq toRequest(String name) {
        AuthorRq rq = new AuthorRq();
        rq.setName(name);
        return rq;
    }

    public AuthorRs toResponse(AuthorEntity entity) {
        AuthorRs rs = new AuthorRs();
        rs.setId(entity.getId());
        rs.setName(entity.getName());
        return rs;
    }

    public List<AuthorRs> toResponse(Iterable<AuthorEntity> entities) {
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    public AuthorEntity toEntity(AuthorRq request) {
        return new AuthorEntity(request.getName());
    }
}