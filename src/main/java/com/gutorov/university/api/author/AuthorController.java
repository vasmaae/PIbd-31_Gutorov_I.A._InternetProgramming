package com.gutorov.university.api.author;

import com.gutorov.university.api.page.PageHelper;
import com.gutorov.university.api.page.PageRs;
import com.gutorov.university.config.Constants;
import com.gutorov.university.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(Constants.API_URL + com.gutorov.university.api.author.AuthorController.URL)
public class AuthorController {
    static final String URL = "/authors";
    private final AuthorService authorService;

    AuthorController(AuthorService authorService) {

        this.authorService = authorService;
    }

    @GetMapping
    public PageRs<AuthorRs> getAuthors(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        return authorService.getAll(PageHelper.toPageable(page, size));
    }

    @GetMapping("/{id}")
    public AuthorRs get(@PathVariable UUID id) {
        return authorService.get(id);
    }

    @PostMapping
    public AuthorRs create(@RequestBody @Valid AuthorRq newAuthor) {
        return authorService.create(newAuthor);
    }

    @PutMapping("/{id}")
    public AuthorRs update(@PathVariable UUID id, @RequestBody @Valid AuthorRq newAuthor) {
        return authorService.update(id, newAuthor);
    }

    @DeleteMapping("/{id}")
    public AuthorRs delete(@PathVariable UUID id) {
        return authorService.delete(id);
    }
}
