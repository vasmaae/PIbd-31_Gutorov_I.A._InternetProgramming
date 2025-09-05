package com.gutorov.university.dto;

import java.time.LocalDateTime;

public class NewsDto {
    private String id;
    private String title;
    private String content;
    private String categoryId;
    private CategoryDto category;
    private String authorId;
    private AuthorDto author;
    private LocalDateTime postDate;

    public NewsDto() {
    }

    public NewsDto(String id, String title, String content, String categoryId, String authorId, LocalDateTime postDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.postDate = postDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}
