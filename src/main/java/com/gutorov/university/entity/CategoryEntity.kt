package com.gutorov.university.entity

import jakarta.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity(
    @Column(nullable = false, length = 512) var name: String,
) : BaseEntity() {
    @OneToMany(mappedBy = "_category", cascade = [CascadeType.ALL], orphanRemoval = true)
    var news: MutableSet<NewsEntity> = hashSetOf()
        private set

    fun addNews(pieceOfNews: NewsEntity) = news.add(pieceOfNews.also { it.category = this })
    fun removeNews(pieceOfNews: NewsEntity) = news.remove(pieceOfNews.also { it.category = null })
}
