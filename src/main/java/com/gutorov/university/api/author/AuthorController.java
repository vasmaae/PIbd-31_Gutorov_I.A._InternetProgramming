package com.gutorov.university.api.author;

import com.gutorov.university.config.Constants;
import com.gutorov.university.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<AuthorRs> getAuthors() {
        return authorService.getAll();
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
