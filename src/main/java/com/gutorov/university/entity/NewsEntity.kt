package com.gutorov.university.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "news")
class NewsEntity(
    @Column(nullable = false, length = 512) var title: String,
    @Column(nullable = false) var content: String,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id", nullable = false)
    private var _category: CategoryEntity?,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "author_id", nullable = false)
    private var _author: AuthorEntity?,
    @Column(nullable = false) var postDate: LocalDateTime,
) : BaseEntity() {
    var category: CategoryEntity?
        get() = _category
        set(value) {
            if (this._category != value) {
                this._category?.removeNews(this)
                this._category = value
                value?.addNews(this)
            }
        }


    var author: AuthorEntity?
        get() = _author
        set(value) {
            if (this._author != value) {
                this._author?.removeNews(this)
                this._author = value
                value?.addNews(this)
            }
        }
}
