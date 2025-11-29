package com.gutorov.university.service;

import com.gutorov.university.api.author.AuthorRq;
import com.gutorov.university.api.author.AuthorRs;
import com.gutorov.university.entity.AuthorEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public AuthorEntity getEntity(UUID id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(AuthorEntity.class, id.toString()));
    }

    @Transactional(readOnly = true)
    public List<AuthorRs> getAll() {
        return AuthorRs.fromEntityList(authorRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AuthorRs get(UUID id) {
        return AuthorRs.fromEntity(getEntity(id));
    }

    @Transactional
    public AuthorRs create(AuthorRq request) {
        var entity = new AuthorEntity(request.getName());
        entity = authorRepository.save(entity);
        return AuthorRs.fromEntity(entity);
    }

    @Transactional
    public AuthorRs update(UUID id, AuthorRq request) {
        var entity = getEntity(id);
        entity.setName(request.getName());
        entity = authorRepository.save(entity);
        return AuthorRs.fromEntity(entity);
    }

    @Transactional
    public AuthorRs delete(UUID id) {
        final var entity = getEntity(id);
        authorRepository.delete(entity);
        return AuthorRs.fromEntity(entity);
    }
}