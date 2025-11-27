package com.gutorov.university.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "news")
class NewsEntity(
    @Column(nullable = false, length = 512) var title: String,
    @Column(nullable = false) var content: String,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id", nullable = false)
    var category: CategoryEntity?,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "author_id", nullable = false)
    var author: AuthorEntity?,
    @Column(nullable = false) var postDate: LocalDateTime,
) : BaseEntity() {
    fun setCategory(category: CategoryEntity?) {
        if (this.category != category) {
            this.category?.removeNews(this)
            this.category = category
            category?.addNews(this)
        }
    }

    fun setAuthor(author: AuthorEntity?) {
        if (this.author != author) {
            this.author?.removeNews(this)
            this.author = author
            author?.addNews(this)
        }
    }
}
