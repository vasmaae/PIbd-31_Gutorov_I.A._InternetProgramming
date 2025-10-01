package com.gutorov.university.service;

import com.gutorov.university.api.news.NewsRq;
import com.gutorov.university.api.news.NewsRs;
import com.gutorov.university.entity.NewsEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.NewsMapper;
import com.gutorov.university.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NewsService {
    private final Logger log = LoggerFactory.getLogger(NewsService.class);
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, CategoryService categoryService, AuthorService authorService) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public NewsEntity getEntity(UUID id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NewsEntity.class, id.toString()));
    }

    public List<NewsRs> getAll() {
        return newsMapper.toResponse(newsRepository.findAll());
    }

    public NewsRs get(UUID id) {
        return newsMapper.toResponse(getEntity(id));
    }

    public NewsRs create(NewsRq request) {
        final var entity = newsRepository.save(newsMapper.toEntity(request));
        return newsMapper.toResponse(entity);
    }

    public NewsRs update(UUID id, NewsRq request) {
        log.info("{}", id);
        var entity = getEntity(id);
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setCategory(categoryService.getEntity(request.getCategoryId()));
        entity.setAuthor(authorService.getEntity(request.getAuthorId()));
        entity.setPostDate(request.getPostDate());
        entity = newsRepository.save(entity);
        return newsMapper.toResponse(entity);
    }

    public NewsRs delete(UUID id) {
        final var entity = getEntity(id);
        newsRepository.delete(entity);
        return newsMapper.toResponse(entity);
    }
}