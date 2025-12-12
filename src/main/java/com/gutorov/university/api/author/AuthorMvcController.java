package com.gutorov.university.api.author;

import com.gutorov.university.service.AuthorService;
import com.gutorov.university.util.WebHelper;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("/mvc/authors")
public class AuthorMvcController {

    private final AuthorService authorService;
    private final WebHelper webHelper;

    public AuthorMvcController(AuthorService authorService, WebHelper webHelper) {
        this.authorService = authorService;
        this.webHelper = webHelper;
    }

    @GetMapping
    public String listAuthors(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam Optional<Integer> size) {
        int pageSize = size.orElse(Integer.parseInt(webHelper.getCookie("pageSize", "5")));
        size.ifPresent(s -> webHelper.setCookie("pageSize", String.valueOf(s)));

        Pageable pageable = PageRequest.of(page, pageSize);
        model.addAttribute("authorsPage", authorService.getAll(pageable));
        model.addAttribute("pageSize", pageSize);
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
