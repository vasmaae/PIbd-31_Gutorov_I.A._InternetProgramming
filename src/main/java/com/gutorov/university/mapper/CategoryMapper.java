package com.gutorov.university.mapper;

import com.gutorov.university.api.category.CategoryRq;
import com.gutorov.university.api.category.CategoryRs;
import com.gutorov.university.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class CategoryMapper {
    public CategoryRq toRequest(String name) {
        CategoryRq rq = new CategoryRq();
        rq.setName(name);
        return rq;
    }

    public CategoryRs toResponse(CategoryEntity entity) {
        CategoryRs rs = new CategoryRs();
        rs.setId(entity.getId());
        rs.setName(entity.getName());
        return rs;
    }

    public List<CategoryRs> toResponse(Iterable<CategoryEntity> entities) {
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    public CategoryEntity toEntity(CategoryRq request) {
        return new CategoryEntity(request.getName());
    }
}