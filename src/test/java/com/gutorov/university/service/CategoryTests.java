package com.gutorov.university.service;

import com.gutorov.university.api.category.CategoryRq;
import com.gutorov.university.api.category.CategoryRs;
import com.gutorov.university.entity.CategoryEntity;
import com.gutorov.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback
public class CategoryTests {
    @Autowired
    private CategoryService service;

    @BeforeEach
    void setUp() {
        for (final var category : service.getAll())
            service.delete(category.getId());
    }

    @Test
    void getEntity_WithUnexistingId_ThrowsNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getEntity(UUID.randomUUID()));
    }

    @Test
    void getEntity_WithExistingId_ReturnsCategory() {
        final var category = service.create(new CategoryRq("Category 1"));
        assertCategoriesAreEqual(category, service.getEntity(category.getId()));
    }

    @Test
    void getAll_WithNotEmptyRepository_ReturnsCategorys() {
        service.create(new CategoryRq("Category 1"));
        service.create(new CategoryRq("Category 2"));
        service.create(new CategoryRq("Category 3"));
        Assertions.assertEquals(3, service.getAll().size());
    }

    @Test
    void get_WithNotEmptyRepository_ReturnsCategory() {
        final var category = service.create(new CategoryRq("Category 1"));
        assertCategoriesAreEqual(category, service.get(category.getId()));
    }

    @Test
    void create_WithValidData_ReturnCategory() {
        final var createdCategory = service.create(new CategoryRq("Category 1"));
        assertCategoriesAreEqual(createdCategory, service.get(createdCategory.getId()));
    }

    @Test
    void update_WithValidData_ReturnsUpdatedCategory() {
        final var createdCategoryId = service.create(new CategoryRq("Category 1")).getId();
        final var updatedCategory = service.update(createdCategoryId, new CategoryRq("Category 2"));
        assertCategoriesAreEqual(updatedCategory, service.get(updatedCategory.getId()));
    }

    @Test
    void delete_WithUnexistingId_CategoryDeletes() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    @Test
    void delete_WithExistingId_CategoryDeletes() {
        final var createdCategoryId = service.create(new CategoryRq("Category 1")).getId();
        service.delete(createdCategoryId);
        Assertions.assertEquals(0, service.getAll().size());
    }

    private void assertCategoriesAreEqual(CategoryRs expected, CategoryRs actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    private void assertCategoriesAreEqual(CategoryRs expected, CategoryEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }
}