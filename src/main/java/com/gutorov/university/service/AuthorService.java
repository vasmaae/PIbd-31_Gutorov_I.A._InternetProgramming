package com.gutorov.university.service;

import com.gutorov.university.api.author.AuthorRq;
import com.gutorov.university.api.author.AuthorRs;
import com.gutorov.university.entity.AuthorEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.AuthorMapper;
import com.gutorov.university.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository,
                         AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorEntity getEntity(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AuthorEntity.class, id.toString()));
    }

    public List<AuthorRs> getAll() {
        return authorMapper.toResponse(authorRepository.findAll());
    }

    public AuthorRs get(UUID id) {
        return authorMapper.toResponse(getEntity(id));
    }

    public AuthorRs create(AuthorRq request) {
        AuthorEntity saved = authorRepository.save(authorMapper.toEntity(request));
        return authorMapper.toResponse(saved);
    }

    public AuthorRs update(UUID id, AuthorRq request) {
        AuthorEntity entity = getEntity(id);
        entity.setName(request.getName());
        AuthorEntity saved = authorRepository.save(entity);
        return authorMapper.toResponse(saved);
    }

    public AuthorRs delete(UUID id) {
        AuthorEntity entity = getEntity(id);
        authorRepository.delete(entity);
        return authorMapper.toResponse(entity);
    }
}