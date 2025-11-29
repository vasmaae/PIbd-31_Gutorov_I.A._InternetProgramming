package com.gutorov.university.repository

import com.gutorov.university.entity.CategoryEntity
import com.gutorov.university.entity.NewsEntity
import com.gutorov.university.entity.projection.MonthlyNews
import com.gutorov.university.entity.projection.NewsByCategory
import com.gutorov.university.entity.projection.NewsOverallStats
import com.gutorov.university.entity.projection.TopAuthor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.*

interface NewsRepository : JpaRepository<NewsEntity, UUID> {
    // 1. Количество новостей по каждой категории
    @Query(
        """
        SELECT c.name AS categoryName, 
               COUNT(n) AS newsCount
        FROM NewsEntity n
        JOIN n.category c
        GROUP BY c.id, c.name
        ORDER BY newsCount DESC
    """
    )
    fun getNewsCountByCategory(): List<NewsByCategory>

    // 2. Топ активных авторов (по количеству опубликованных новостей)
    @Query(
        """
        SELECT a.name AS authorName, COUNT(n) AS newsCount
        FROM NewsEntity n
        JOIN n.author a
        GROUP BY a.id, a.name
        ORDER BY newsCount DESC
        LIMIT :limit
    """
    )
    fun getTopAuthors(@Param("limit") limit: Int = 5): List<TopAuthor>

    // 3. Динамика публикаций по месяцам (за всё время или за период)
    @Query(
        """
        SELECT YEAR(n.postDate) AS year,
               MONTH(n.postDate) AS month,
               COUNT(n) AS newsCount
        FROM NewsEntity n
        WHERE (:from IS NULL OR n.postDate >= :from)
          AND (:to IS NULL OR n.postDate < :to)
        GROUP BY YEAR(n.postDate), MONTH(n.postDate)
        ORDER BY YEAR(n.postDate) DESC, MONTH(n.postDate) DESC
    """
    )
    fun getNewsByMonth(
        @Param("from") from: LocalDateTime? = null, @Param("to") to: LocalDateTime? = null
    ): List<MonthlyNews>

    // 4. Общая статистика за период
    @Query(
        """
        SELECT COUNT(n) AS totalNews,
               COUNT(DISTINCT n.author) AS uniqueAuthors,
               COUNT(DISTINCT n.category) AS usedCategories
        FROM NewsEntity n
        WHERE (:from IS NULL OR n.postDate >= :from)
          AND (:to IS NULL OR n.postDate < :to)
    """
    )
    fun getOverallNewsStats(
        @Param("from") from: LocalDateTime?, @Param("to") to: LocalDateTime?
    ): NewsOverallStats?

    // 5. Новости конкретной категории за период (полный список сущностей, если нужен для детализации)
    fun findByCategoryAndPostDateBetween(
        category: CategoryEntity, from: LocalDateTime, to: LocalDateTime
    ): List<NewsEntity>
}