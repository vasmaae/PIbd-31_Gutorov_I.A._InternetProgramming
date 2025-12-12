package com.gutorov.university.api.author;

import com.gutorov.university.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/mvc/authors")
public class AuthorMvcController {

    private final AuthorService authorService;

    public AuthorMvcController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("authorsPage", authorService.getAll(pageable));
        model.addAttribute("pageSize", size);
        return "authors/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new AuthorRs());
        return "authors/form";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute("author") AuthorRs author, BindingResult result) {
        if (result.hasErrors()) {
            return "authors/form";
        }
        authorService.create(new AuthorRq(author.getName()));
        return "redirect:/mvc/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        model.addAttribute("author", authorService.get(id));
        return "authors/form";
    }

    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable UUID id, @Valid @ModelAttribute("author") AuthorRs author, BindingResult result) {
        if (result.hasErrors()) {
            return "authors/form";
        }
        authorService.update(id, new AuthorRq(author.getName()));
        return "redirect:/mvc/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable UUID id) {
        authorService.delete(id);
        return "redirect:/mvc/authors";
    }
}
