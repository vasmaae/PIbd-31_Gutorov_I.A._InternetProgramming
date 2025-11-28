package com.gutorov.university.repository

import com.gutorov.university.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<CategoryEntity, UUID>
