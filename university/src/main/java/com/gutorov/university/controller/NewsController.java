package com.gutorov.university.controller;

import com.gutorov.university.config.Constants;
import com.gutorov.university.dto.NewsDto;
import com.gutorov.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping(Constants.API_URL + NewsController.URL)
class NewsController {
    static final String URL = "/news";
    private final Logger log = LoggerFactory.getLogger(NewsController.class);
    private final ConcurrentLinkedDeque<NewsDto> news;
    private final CategoriesController categoriesController;
    private final AuthorsController authorsController;

    public NewsController(CategoriesController categoriesController, AuthorsController authorsController) {
        this.categoriesController = categoriesController;
        this.authorsController = authorsController;
        var categories = categoriesController.getCategories();
        var authors = authorsController.getAuthors();
        this.news = new ConcurrentLinkedDeque<>(List.of(
                new NewsDto(UUID.randomUUID().toString(), "Title 1", "Content 1", categories.get(0).getId(), authors.get(0).getId(),
                        LocalDateTime.of(2025, Month.SEPTEMBER, 5, 17, 30, 0)),
                new NewsDto(UUID.randomUUID().toString(), "Title 2", "Content 2", categories.get(1).getId(), authors.get(1).getId(),
                        LocalDateTime.of(2025, Month.SEPTEMBER, 5, 17, 40, 10)),
                new NewsDto(UUID.randomUUID().toString(), "Title 3", "Content 3", categories.get(2).getId(), authors.get(2).getId(),
                        LocalDateTime.of(2025, Month.SEPTEMBER, 5, 17, 50, 20))
        ));
    }

    @GetMapping
    public List<NewsDto> getNews() {
        var response = news.stream().toList();
        log.debug("get all: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public NewsDto get(@PathVariable String id) {
        var response = news.stream()
                .filter(News -> News.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException(NewsDto.class, id));
        log.debug("get by id \"{}\": {}", id, response);
        return response;
    }

    @PostMapping
    public NewsDto create(@RequestBody NewsDto newNews) {
        newNews.setId(UUID.randomUUID().toString());
        newNews.setCategory(categoriesController.get(newNews.getCategoryId()));
        newNews.setAuthor(authorsController.get(newNews.getAuthorId()));
        log.debug("create: {}", newNews);
        news.add(newNews);
        return newNews;
    }

    @PutMapping("/{id}")
    public NewsDto update(@PathVariable String id, @RequestBody NewsDto newNews) {
        log.debug("update by id \"{}\": {}", id, newNews);
        final var news = get(id);
        news.setTitle(newNews.getTitle());
        news.setContent(newNews.getContent());
        news.setCategoryId(newNews.getCategoryId());
        news.setCategory(newNews.getCategory());
        news.setAuthorId(newNews.getAuthorId());
        news.setAuthor(newNews.getAuthor());
        news.setPostDate(newNews.getPostDate());
        return news;
    }

    @DeleteMapping("/{id}")
    public NewsDto delete(@PathVariable String id) {
        final var news = get(id);
        log.debug("delete by id \"{}\": {}", id, news);
        this.news.remove(news);
        return news;
    }
}
