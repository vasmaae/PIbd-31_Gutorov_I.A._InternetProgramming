package com.gutorov.university.service;

import com.gutorov.university.api.news.NewsRs;
import com.gutorov.university.entity.NewsEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.AuthorMapper;
import com.gutorov.university.mapper.CategoryMapper;
import com.gutorov.university.mapper.NewsMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Rollback
public class NewsTests {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsMapper newsMapper;

    @BeforeEach
    void setUp() {
        for (final var author : authorService.getAll())
            authorService.delete(author.getId());
        for (final var category : categoryService.getAll())
            categoryService.delete(category.getId());
        for (final var news : newsService.getAll())
            newsService.delete(news.getId());
    }

    @Test
    void getEntity_WithUnexistingId_ThrowsNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> newsService.getEntity(UUID.randomUUID()));
    }

    @Test
    void getEntity_WithExistingId_ReturnsNews() {
        final var author = authorService.create(authorMapper.toRequest("Author 1"));
        final var category = categoryService.create(categoryMapper.toRequest("Category 1"));
        final var news = newsService.create(newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now()));
        assertNewsAreEqual(news, newsService.getEntity(news.getId()));
    }

    @Test
    void getAll_WithNotEmptyRepository_ReturnsNews() {
        final var author = authorService.create(authorMapper.toRequest("Author 1"));
        final var category = categoryService.create(categoryMapper.toRequest("Category 1"));
        newsService.create(newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now()));
        newsService.create(newsMapper.toRequest("News 2", "Content 2", category.getId(), author.getId(), LocalDateTime.now()));
        newsService.create(newsMapper.toRequest("News 3", "Content 3", category.getId(), author.getId(), LocalDateTime.now()));
        Assertions.assertEquals(3, newsService.getAll().size());
    }

    @Test
    void get_WithNotEmptyRepository_ReturnsNews() {
        final var author = authorService.create(authorMapper.toRequest("Author 1"));
        final var category = categoryService.create(categoryMapper.toRequest("Category 1"));
        final var news = newsService.create(newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now()));
        assertNewsAreEqual(news, newsService.get(news.getId()));
    }

    @Test
    void create_WithValidData_ReturnNews() {
        final var author = authorService.create(authorMapper.toRequest("Author 1"));
        final var category = categoryService.create(categoryMapper.toRequest("Category 1"));
        final var createdNews = newsService.create(newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now()));
        assertNewsAreEqual(createdNews, newsService.get(createdNews.getId()));
    }

    @Test
    void update_WithValidData_ReturnsUpdatedNews() {
        final var author = authorService.create(authorMapper.toRequest("Author 1"));
        final var category = categoryService.create(categoryMapper.toRequest("Category 1"));
        final var createdNewsId = newsService.create(newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now()));
        final var updatedNews = newsService.update(createdNewsId.getId(), newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now()));
        assertNewsAreEqual(updatedNews, newsService.get(updatedNews.getId()));
    }

    @Test
    void delete_WithUnexistingId_NewsDeletes() {
        Assertions.assertThrows(NotFoundException.class, () -> newsService.delete(UUID.randomUUID()));
    }

    @Test
    void delete_WithExistingId_NewsDeletes() {
        final var author = authorService.create(authorMapper.toRequest("Author 1"));
        final var category = categoryService.create(categoryMapper.toRequest("Category 1"));
        final var createdNewsId = newsService.create(newsMapper.toRequest("News 1", "Content 1", category.getId(), author.getId(), LocalDateTime.now())).getId();
        newsService.delete(createdNewsId);
        Assertions.assertEquals(0, newsService.getAll().size());
    }

    private void assertNewsAreEqual(NewsRs expected, NewsRs actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getContent(), actual.getContent());
        Assertions.assertEquals(expected.getCategory().getId(), actual.getCategory().getId());
        Assertions.assertEquals(expected.getCategory().getName(), actual.getCategory().getName());
        Assertions.assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        Assertions.assertEquals(expected.getAuthor().getName(), actual.getAuthor().getName());
        Assertions.assertEquals(expected.getPostDate(), actual.getPostDate());
    }

    private void assertNewsAreEqual(NewsRs expected, NewsEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getContent(), actual.getContent());
        Assertions.assertEquals(expected.getCategory().getId(), actual.getCategory().getId());
        Assertions.assertEquals(expected.getCategory().getName(), actual.getCategory().getName());
        Assertions.assertEquals(expected.getAuthor().getId(), actual.getAuthor().getId());
        Assertions.assertEquals(expected.getAuthor().getName(), actual.getAuthor().getName());
        Assertions.assertEquals(expected.getPostDate(), actual.getPostDate());
    }
}
