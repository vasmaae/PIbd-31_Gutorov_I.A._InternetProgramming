package com.gutorov.university.service;

import com.gutorov.university.api.category.CategoryRq;
import com.gutorov.university.api.category.CategoryRs;
import com.gutorov.university.entity.CategoryEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public CategoryEntity getEntity(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(CategoryEntity.class, id.toString()));
    }

    @Transactional(readOnly = true)
    public List<CategoryRs> getAll() {
        return CategoryRs.fromEntityList(categoryRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CategoryRs get(UUID id) {
        return CategoryRs.fromEntity(getEntity(id));
    }

    @Transactional
    public CategoryRs create(CategoryRq request) {
        var entity = new CategoryEntity(request.getName());
        entity = categoryRepository.save(entity);
        return CategoryRs.fromEntity(entity);
    }

    @Transactional
    public CategoryRs update(UUID id, CategoryRq request) {
        var entity = getEntity(id);
        entity.setName(request.getName());
        entity = categoryRepository.save(entity);
        return CategoryRs.fromEntity(entity);
    }

    @Transactional
    public CategoryRs delete(UUID id) {
        final var entity = getEntity(id);
        categoryRepository.delete(entity);
        return CategoryRs.fromEntity(entity);
    }
}