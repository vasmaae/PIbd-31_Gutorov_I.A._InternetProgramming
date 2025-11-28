package com.gutorov.university.repository

import com.gutorov.university.entity.ApplicationEntity
import com.gutorov.university.entity.ProgramEntity
import com.gutorov.university.entity.projection.MonthlyApplications
import com.gutorov.university.entity.projection.OverallStatsProjection
import com.gutorov.university.entity.projection.ProgramApplicationsStats
import com.gutorov.university.entity.projection.ProgramPopularity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.*

interface ApplicationRepository : JpaRepository<ApplicationEntity, UUID> {
    // 1. Статистика по программам: сколько подано и сколько принято
    @Query(
        """
        SELECT p.name as programName, 
               COUNT(a) as totalApplications,
               SUM(CASE WHEN a.isAdmitted = true THEN 1 ELSE 0 END) as admittedCount,
               ROUND(
                   100.0 * SUM(CASE WHEN a.isAdmitted = true THEN 1 ELSE 0 END) / COUNT(a), 2
               ) as admissionRate
        FROM ApplicationEntity a 
        JOIN a.program p 
        GROUP BY p.id, p.name 
        ORDER BY totalApplications DESC
    """
    )
    fun getApplicationsStatsByProgram(): List<ProgramApplicationsStats>

    // 2. Топ-N самых популярных программ
    @Query(
        """
        SELECT p.name as programName, COUNT(a) as totalApplications
        FROM ApplicationEntity a 
        JOIN a.program p 
        GROUP BY p.id, p.name 
        ORDER BY totalApplications DESC
    """
    )
    fun getTopPopularPrograms(@Param("limit") limit: Int = 5): List<ProgramPopularity>

    // 3. Динамика подачи заявок по месяцам (за всё время или за период)
    @Query(
        """
        SELECT YEAR(a.submissionDate) as year,
               MONTH(a.submissionDate) as month,
               COUNT(a) as applicationsCount
        FROM ApplicationEntity a
        WHERE (:from IS NULL OR a.submissionDate >= :from)
          AND (:to IS NULL OR a.submissionDate < :to)
        GROUP BY YEAR(a.submissionDate), MONTH(a.submissionDate)
        ORDER BY YEAR(a.submissionDate) DESC, MONTH(a.submissionDate) DESC
    """
    )
    fun getApplicationsByMonth(
        @Param("from") from: LocalDateTime? = null,
        @Param("to") to: LocalDateTime? = null
    ): List<MonthlyApplications>

    // 4. Общая статистика за период
    @Query(
        """
        SELECT COUNT(a) as total,
               SUM(CASE WHEN a.isAdmitted = true THEN 1 ELSE 0 END) as admitted,
               SUM(CASE WHEN a.isAdmitted = false THEN 1 ELSE 0 END) as rejected
        FROM ApplicationEntity a
        WHERE (:from IS NULL OR a.submissionDate >= :from)
          AND (:to IS NULL OR a.submissionDate < :to)
    """
    )
    fun getOverallStats(
        @Param("from") from: LocalDateTime?,
        @Param("to") to: LocalDateTime?
    ): OverallStatsProjection?

    // 5. Заявки по конкретной программе (с фильтрацией по статусу и дате)
    fun findByProgramAndSubmissionDateBetweenAndIsAdmitted(
        program: ProgramEntity,
        from: LocalDateTime,
        to: LocalDateTime,
        isAdmitted: Boolean
    ): List<ApplicationEntity>
}