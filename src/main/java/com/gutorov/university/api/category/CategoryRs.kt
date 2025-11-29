package com.gutorov.university.api.category

import com.gutorov.university.entity.CategoryEntity
import java.util.*
import java.util.stream.StreamSupport

class CategoryRs(
    var id: UUID? = null,
    var name: String? = null
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: CategoryEntity?): CategoryRs {
            return CategoryRs(entity?.id, entity?.name)
        }

        @JvmStatic
        fun fromEntityList(entities: List<CategoryEntity>): List<CategoryRs> {
            return StreamSupport.stream(entities.spliterator(), false)
                .map(CategoryRs::fromEntity)
                .toList();
        }
    }
}
