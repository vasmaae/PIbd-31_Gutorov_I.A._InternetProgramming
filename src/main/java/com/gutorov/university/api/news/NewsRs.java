package com.gutorov.university.api.news;

import com.gutorov.university.api.author.AuthorRs;
import com.gutorov.university.api.category.CategoryRs;

import java.time.LocalDateTime;
import java.util.UUID;

public class NewsRs {
    private UUID id;
    private String title;
    private String content;
    private CategoryRs category;
    private AuthorRs author;
    private LocalDateTime postDate;

    public NewsRs() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public CategoryRs getCategory() {
        return category;
    }

    public void setCategory(CategoryRs category) {
        this.category = category;
    }

    public AuthorRs getAuthor() {
        return author;
    }

    public void setAuthor(AuthorRs author) {
        this.author = author;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}
