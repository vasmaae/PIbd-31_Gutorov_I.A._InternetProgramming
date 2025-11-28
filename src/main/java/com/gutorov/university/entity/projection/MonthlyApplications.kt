package com.gutorov.university.entity.projection

interface MonthlyApplications {
    val year: Int
    val month: Int
    val applicationsCount: Long
}