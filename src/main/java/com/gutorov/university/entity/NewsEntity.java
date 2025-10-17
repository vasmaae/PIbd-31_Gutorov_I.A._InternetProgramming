package com.gutorov.university.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class NewsEntity extends BaseEntity {
    private String title;
    private String content;
    private UUID categoryId;
    private CategoryEntity category;
    private UUID authorId;
    private AuthorEntity author;
    private LocalDateTime postDate;

    public NewsEntity() {
        super();
    }

    public NewsEntity(String title, String content, UUID categoryId, UUID authorId, LocalDateTime postDate) {
        this();
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.postDate = postDate;
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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}
