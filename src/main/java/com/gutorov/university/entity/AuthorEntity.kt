package com.gutorov.university.entity

import jakarta.persistence.*

@Entity
@Table(name = "authors")
class AuthorEntity(
    @Column(nullable = false, length = 512) var name: String,
) : BaseEntity() {
    @OneToMany(mappedBy = "_author", cascade = [CascadeType.ALL], orphanRemoval = true)
    var news: MutableSet<NewsEntity> = hashSetOf()
        private set

    constructor() : this("")

    fun addNews(pieceOfNews: NewsEntity) = news.add(pieceOfNews.also { it.author = this })
    fun removeNews(pieceOfNews: NewsEntity) = news.remove(pieceOfNews.also { it.author = null })
}