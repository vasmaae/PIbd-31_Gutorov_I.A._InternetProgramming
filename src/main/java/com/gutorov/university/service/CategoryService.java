package com.gutorov.university.service;

import com.gutorov.university.api.category.CategoryRq;
import com.gutorov.university.api.category.CategoryRs;
import com.gutorov.university.entity.CategoryEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.CategoryMapper;
import com.gutorov.university.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public CategoryEntity getEntity(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CategoryEntity.class, id.toString()));
    }

    @Transactional(readOnly = true)
    public List<CategoryRs> getAll() {
        return categoryMapper.toResponse(categoryRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CategoryRs get(UUID id) {
        return categoryMapper.toResponse(getEntity(id));
    }

    @Transactional
    public CategoryRs create(CategoryRq request) {
        CategoryEntity saved = categoryRepository.save(categoryMapper.toEntity(request));
        return categoryMapper.toResponse(saved);
    }

    @Transactional
    public CategoryRs update(UUID id, CategoryRq request) {
        CategoryEntity entity = getEntity(id);
        entity.setName(request.getName());
        CategoryEntity saved = categoryRepository.save(entity);
        return categoryMapper.toResponse(saved);
    }

    @Transactional
    public CategoryRs delete(UUID id) {
        CategoryEntity entity = getEntity(id);
        categoryRepository.delete(entity);
        return categoryMapper.toResponse(entity);
    }
}