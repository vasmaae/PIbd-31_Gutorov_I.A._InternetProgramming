package com.gutorov.university.controller;

import com.gutorov.university.config.Constants;
import com.gutorov.university.dto.AuthorDto;
import com.gutorov.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping(Constants.API_URL + AuthorsController.URL)
class AuthorsController {
    static final String URL = "/authors";
    private final Logger log = LoggerFactory.getLogger(AuthorsController.class);
    private final ConcurrentLinkedDeque<AuthorDto> authors;

    public AuthorsController() {
        this.authors = new ConcurrentLinkedDeque<>(List.of(
                new AuthorDto(UUID.randomUUID().toString(), "Anton Antonov"),
                new AuthorDto(UUID.randomUUID().toString(), "Semen Semenov"),
                new AuthorDto(UUID.randomUUID().toString(), "Maxim Maximov")
        ));
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        var response = authors.stream().toList();
        log.debug("get all: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public AuthorDto get(@PathVariable String id) {
        var response = authors.stream()
                .filter(author -> author.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException(AuthorDto.class, id));
        log.debug("get by id \"{}\": {}", id, response);
        return response;
    }

    @PostMapping
    public AuthorDto create(@RequestBody AuthorDto newAuthor) {
        newAuthor.setId(UUID.randomUUID().toString());
        log.debug("create: {}", newAuthor);
        authors.add(newAuthor);
        return newAuthor;
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable String id, @RequestBody AuthorDto newAuthor) {
        log.debug("update by id \"{}\": {}", id, newAuthor);
        final var author = get(id);
        author.setName(newAuthor.getName());
        return author;
    }

    @DeleteMapping("/{id}")
    public AuthorDto delete(@PathVariable String id) {
        final var author = get(id);
        log.debug("delete by id \"{}\": {}", id, author);
        authors.remove(author);
        return author;
    }
}
