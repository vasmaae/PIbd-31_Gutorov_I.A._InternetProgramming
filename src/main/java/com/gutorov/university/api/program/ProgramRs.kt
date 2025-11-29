package com.gutorov.university.api.program

import com.gutorov.university.entity.ProgramEntity
import java.util.*
import java.util.stream.StreamSupport

data class ProgramRs(
    var id: UUID? = null,
    var name: String? = null
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: ProgramEntity?): ProgramRs {
            return ProgramRs(entity?.id, entity?.name)
        }

        @JvmStatic
        fun fromEntityList(entities: List<ProgramEntity>): List<ProgramRs> {
            return StreamSupport.stream(entities.spliterator(), false)
                .map(ProgramRs::fromEntity)
                .toList();
        }
    }
}