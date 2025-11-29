package com.gutorov.university.api.news

import com.gutorov.university.api.author.AuthorRs
import com.gutorov.university.api.category.CategoryRs
import com.gutorov.university.entity.NewsEntity
import java.time.LocalDateTime
import java.util.*
import java.util.stream.StreamSupport

class NewsRs(
    var id: UUID? = null,
    var title: String? = null,
    var content: String? = null,
    var category: CategoryRs? = null,
    var author: AuthorRs? = null,
    var postDate: LocalDateTime? = null
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: NewsEntity): NewsRs {
            return NewsRs(
                entity.id,
                entity.title,
                entity.content,
                CategoryRs.fromEntity(entity.category),
                AuthorRs.fromEntity(entity.author),
                entity.postDate
            )
        }

        @JvmStatic
        fun fromEntityList(entities: List<NewsEntity>): List<NewsRs> {
            return StreamSupport.stream(entities.spliterator(), false)
                .map(NewsRs::fromEntity)
                .toList();
        }
    }
}
