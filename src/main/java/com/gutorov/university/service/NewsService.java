package com.gutorov.university.service;

import com.gutorov.university.api.news.NewsRq;
import com.gutorov.university.api.news.NewsRs;
import com.gutorov.university.entity.NewsEntity;
import com.gutorov.university.entity.projection.MonthlyNews;
import com.gutorov.university.entity.projection.NewsByCategory;
import com.gutorov.university.entity.projection.NewsOverallStats;
import com.gutorov.university.entity.projection.TopAuthor;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public NewsService(NewsRepository newsRepository, CategoryService categoryService, AuthorService authorService) {
        this.newsRepository = newsRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public NewsEntity getEntity(UUID id) {
        return newsRepository.findById(id).orElseThrow(() -> new NotFoundException(NewsEntity.class, id.toString()));
    }

    @Transactional(readOnly = true)
    public List<NewsRs> getAll() {
        return NewsRs.fromEntityList(newsRepository.findAll());
    }

    @Transactional(readOnly = true)
    public NewsRs get(UUID id) {
        return NewsRs.fromEntity(getEntity(id));
    }

    @Transactional
    public NewsRs create(NewsRq request) {
        final var category = categoryService.getEntity(request.getCategoryId());
        final var author = authorService.getEntity(request.getAuthorId());

        var entity = new NewsEntity(request.getTitle(), request.getContent(), category, author, request.getPostDate());
        entity = newsRepository.save(entity);
        return NewsRs.fromEntity(entity);
    }

    @Transactional
    public NewsRs update(UUID id, NewsRq request) {
        var entity = getEntity(id);
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setPostDate(request.getPostDate());
        entity.changeCategory(categoryService.getEntity(request.getCategoryId()));
        entity.changeAuthor(authorService.getEntity(request.getAuthorId()));
        entity = newsRepository.save(entity);
        return NewsRs.fromEntity(entity);
    }

    @Transactional
    public NewsRs delete(UUID id) {
        final var entity = getEntity(id);
        newsRepository.delete(entity);
        return NewsRs.fromEntity(entity);
    }

    @Transactional(readOnly = true)
    public List<NewsByCategory> getNewsCountByCategory() {
        return newsRepository.getNewsCountByCategory();
    }

    @Transactional(readOnly = true)
    public List<MonthlyNews> getNewsByMonth(LocalDateTime from, LocalDateTime to) {
        return newsRepository.getNewsByMonth(from, to);
    }

    @Transactional(readOnly = true)
    public List<TopAuthor> getTopAuthors(int limit) {
        return newsRepository.getTopAuthors(limit);
    }

    @Transactional(readOnly = true)
    public List<TopAuthor> getTopAuthors() {
        return newsRepository.getTopAuthors(5);
    }

    @Transactional(readOnly = true)
    public NewsOverallStats getOverallNewsStats(LocalDateTime from, LocalDateTime to) {
        return newsRepository.getOverallNewsStats(from, to);
    }

    @Transactional(readOnly = true)
    public List<NewsEntity> getNewsByCategoryAndPeriod(UUID categoryId, LocalDateTime from, LocalDateTime to) {
        final var category = categoryService.getEntity(categoryId);
        return newsRepository.findByCategoryAndPostDateBetween(category, from, to);
    }
}