package com.gutorov.university.api.category;

import com.gutorov.university.api.category.CategoryRq;
import com.gutorov.university.api.category.CategoryRs;
import com.gutorov.university.config.Constants;
import com.gutorov.university.entity.CategoryEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping(Constants.API_URL + com.gutorov.university.api.category.CategoryController.URL)
public class CategoryController {
    static final String URL = "/categories";
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryRs> getCategorys() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryRs get(@PathVariable UUID id) {
        return categoryService.get(id);
    }

    @PostMapping
    public CategoryRs create(@RequestBody @Valid CategoryRq newCategory) {
        return categoryService.create(newCategory);
    }

    @PutMapping("/{id}")
    public CategoryRs update(@PathVariable UUID id, @RequestBody @Valid CategoryRq newCategory) {
        return categoryService.update(id, newCategory);
    }

    @DeleteMapping("/{id}")
    public CategoryRs delete(@PathVariable UUID id) {
        return categoryService.delete(id);
    }
}
