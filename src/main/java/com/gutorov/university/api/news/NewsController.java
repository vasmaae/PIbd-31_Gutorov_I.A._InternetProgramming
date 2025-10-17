package com.gutorov.university.api.news;

import com.gutorov.university.config.Constants;
import com.gutorov.university.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.API_URL + com.gutorov.university.api.news.NewsController.URL)
public class NewsController {
    static final String URL = "/news";
    private final NewsService newsService;

    NewsController(NewsService newsService) {

        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsRs> getNews() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    public NewsRs get(@PathVariable UUID id) {
        return newsService.get(id);
    }

    @PostMapping
    public NewsRs create(@RequestBody @Valid NewsRq newNews) {
        return newsService.create(newNews);
    }

    @PutMapping("/{id}")
    public NewsRs update(@PathVariable UUID id, @RequestBody @Valid NewsRq newNews) {
        return newsService.update(id, newNews);
    }

    @DeleteMapping("/{id}")
    public NewsRs delete(@PathVariable UUID id) {
        return newsService.delete(id);
    }
}
