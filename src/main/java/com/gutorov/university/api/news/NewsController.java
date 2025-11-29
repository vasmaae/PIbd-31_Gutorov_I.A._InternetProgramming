package com.gutorov.university.api.news;

import com.gutorov.university.config.Constants;
import com.gutorov.university.entity.NewsEntity;
import com.gutorov.university.entity.projection.MonthlyNews;
import com.gutorov.university.entity.projection.NewsByCategory;
import com.gutorov.university.entity.projection.NewsOverallStats;
import com.gutorov.university.entity.projection.TopAuthor;
import com.gutorov.university.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/stats/by-category")
    public List<NewsByCategory> getNewsByCategory() {
        return newsService.getNewsCountByCategory();
    }

    @GetMapping("/stats/monthly")
    public List<MonthlyNews> getMonthlyNews(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return newsService.getNewsByMonth(from, to);
    }

    @GetMapping("/stats/top-authors")
    public List<TopAuthor> getTopAuthors(@RequestParam(defaultValue = "5") int limit) {
        return newsService.getTopAuthors(limit);
    }

    @GetMapping("/stats/overall")
    public NewsOverallStats getOverallStats(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return newsService.getOverallNewsStats(from, to);
    }

    @GetMapping("/stats/by-category/{categoryId}")
    public List<NewsRs> getNewsByCategoryAndPeriod(
            @PathVariable UUID categoryId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<NewsEntity> entities = newsService.getNewsByCategoryAndPeriod(categoryId, from, to);
        return NewsRs.fromEntityList(entities);
    }
}
