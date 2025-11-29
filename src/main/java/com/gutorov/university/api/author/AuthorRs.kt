package com.gutorov.university.api.author

import com.gutorov.university.entity.AuthorEntity
import java.util.*
import java.util.stream.StreamSupport

class AuthorRs(
    var id: UUID? = null,
    var name: String? = null
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: AuthorEntity?): AuthorRs {
            return AuthorRs(entity?.id, entity?.name)
        }

        @JvmStatic
        fun fromEntityList(entities: List<AuthorEntity>): List<AuthorRs> {
            return StreamSupport.stream(entities.spliterator(), false)
                .map(AuthorRs::fromEntity)
                .toList();
        }
    }
}
