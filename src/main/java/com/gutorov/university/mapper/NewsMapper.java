package com.gutorov.university.mapper;

import com.gutorov.university.api.news.NewsRq;
import com.gutorov.university.api.news.NewsRs;
import com.gutorov.university.entity.NewsEntity;
import com.gutorov.university.service.AuthorService;
import com.gutorov.university.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class NewsMapper {
    private final CategoryMapper categoryMapper;
    private final AuthorMapper authorMapper;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public NewsMapper(CategoryMapper categoryMapper,
                      AuthorMapper authorMapper,
                      CategoryService categoryService,
                      AuthorService authorService) {
        this.categoryMapper = categoryMapper;
        this.authorMapper = authorMapper;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public NewsRq toRequest(String title, String content, UUID categoryId, UUID authorId, java.time.LocalDateTime postDate) {
        final NewsRq newsRq = new NewsRq();
        newsRq.setTitle(title);
        newsRq.setContent(content);
        newsRq.setCategoryId(categoryId);
        newsRq.setAuthorId(authorId);
        newsRq.setPostDate(postDate);
        return newsRq;
    }

    public NewsRs toResponse(NewsEntity entity) {
        final NewsRs newsRs = new NewsRs();
        newsRs.setId(entity.getId());
        newsRs.setTitle(entity.getTitle());
        newsRs.setContent(entity.getContent());
        newsRs.setCategory(categoryMapper.toResponse(entity.getCategory()));
        newsRs.setAuthor(authorMapper.toResponse(entity.getAuthor()));
        newsRs.setPostDate(entity.getPostDate());
        return newsRs;
    }

    public List<NewsRs> toResponse(Iterable<NewsEntity> entities) {
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    public NewsEntity toEntity(NewsRq request) {
        return new NewsEntity(
                request.getTitle(),
                request.getContent(),
                categoryService.getEntity(request.getCategoryId()),
                authorService.getEntity(request.getAuthorId()),
                request.getPostDate());
    }
}