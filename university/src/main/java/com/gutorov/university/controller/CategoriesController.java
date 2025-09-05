package com.gutorov.university.controller;

import com.gutorov.university.config.Constants;
import com.gutorov.university.dto.CategoryDto;
import com.gutorov.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping(Constants.API_URL + CategoriesController.URL)
class CategoriesController {
    static final String URL = "/categories";
    private final Logger log = LoggerFactory.getLogger(CategoriesController.class);
    private final ConcurrentLinkedDeque<CategoryDto> categories;

    public CategoriesController() {
        this.categories = new ConcurrentLinkedDeque<>(List.of(
                new CategoryDto(UUID.randomUUID().toString(), "Campus"),
                new CategoryDto(UUID.randomUUID().toString(), "Science"),
                new CategoryDto(UUID.randomUUID().toString(), "Art")
        ));
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        var response = categories.stream().toList();
        log.debug("get all: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable String id) {
        var response = categories.stream()
                .filter(Category -> Category.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException(CategoryDto.class, id));
        log.debug("get by id \"{}\": {}", id, response);
        return response;
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto newCategory) {
        newCategory.setId(UUID.randomUUID().toString());
        log.debug("create: {}", newCategory);
        categories.add(newCategory);
        return newCategory;
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable String id, @RequestBody CategoryDto newCategory) {
        log.debug("update by id \"{}\": {}", id, newCategory);
        final var category = get(id);
        category.setName(newCategory.getName());
        return category;
    }

    @DeleteMapping("/{id}")
    public CategoryDto delete(@PathVariable String id) {
        final var category = get(id);
        log.debug("delete by id \"{}\": {}", id, category);
        categories.remove(category);
        return category;
    }
}
