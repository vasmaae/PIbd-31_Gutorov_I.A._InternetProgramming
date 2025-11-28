package com.gutorov.university.entity.projection

interface OverallStatsProjection {
    val total: Long
    val admitted: Long?
    val rejected: Long?

    val admissionRate: Double
        get() = if (total == 0L) 0.0 else (admitted ?: 0).toDouble() / total * 100
}