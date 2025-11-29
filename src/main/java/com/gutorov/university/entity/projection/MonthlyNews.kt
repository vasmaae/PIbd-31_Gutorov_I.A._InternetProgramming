package com.gutorov.university.entity.projection

interface MonthlyNews {
    val year: Int
    val month: Int
    val newsCount: Long
}