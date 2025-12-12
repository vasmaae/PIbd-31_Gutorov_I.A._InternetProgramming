package com.gutorov.university.api.category;

import com.gutorov.university.api.page.PageHelper;
import com.gutorov.university.api.page.PageRs;
import com.gutorov.university.config.Constants;
import com.gutorov.university.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(Constants.API_URL + com.gutorov.university.api.category.CategoryController.URL)
public class CategoryController {
    static final String URL = "/categories";
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping
    public PageRs<CategoryRs> getCategories(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        return categoryService.getAll(PageHelper.toPageable(page, size));
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
