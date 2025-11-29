package com.gutorov.university.entity.projection

interface NewsByAuthor {
    val authorFullName: String
    val newsCount: Long
}