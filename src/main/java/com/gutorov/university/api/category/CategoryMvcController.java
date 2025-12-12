package com.gutorov.university.api.category;

import com.gutorov.university.service.CategoryService;
import com.gutorov.university.util.WebHelper;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/mvc/categories")
public class CategoryMvcController {

    private final CategoryService categoryService;
    private final WebHelper webHelper;

    public CategoryMvcController(CategoryService categoryService, WebHelper webHelper) {
        this.categoryService = categoryService;
        this.webHelper = webHelper;
    }

    @GetMapping
    public String listCategories(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam Optional<Integer> size) {
        int pageSize = size.orElse(Integer.parseInt(webHelper.getCookie("pageSize", "5")));
        size.ifPresent(s -> webHelper.setCookie("pageSize", String.valueOf(s)));

        Pageable pageable = PageRequest.of(page, pageSize);
        model.addAttribute("categoriesPage", categoryService.getAll(pageable));
        model.addAttribute("pageSize", pageSize);
        return "categories/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new CategoryRs());
        return "categories/form";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") CategoryRs category, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        categoryService.create(new CategoryRq(category.getName()));
        return "redirect:/mvc/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        model.addAttribute("category", categoryService.get(id));
        return "categories/form";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable UUID id, @Valid @ModelAttribute("category") CategoryRs category, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        categoryService.update(id, new CategoryRq(category.getName()));
        return "redirect:/mvc/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return "redirect:/mvc/categories";
    }
}
