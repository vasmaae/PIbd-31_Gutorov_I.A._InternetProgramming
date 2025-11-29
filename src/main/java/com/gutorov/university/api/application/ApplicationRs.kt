package com.gutorov.university.api.application

import com.fasterxml.jackson.annotation.JsonProperty
import com.gutorov.university.api.program.ProgramRs
import com.gutorov.university.entity.ApplicationEntity
import java.time.LocalDateTime
import java.util.*
import java.util.stream.StreamSupport

class ApplicationRs(
    var id: UUID? = null,
    var fullName: String? = null,
    var email: String? = null,
    var program: ProgramRs? = null,
    var submissionDate: LocalDateTime? = null,
    @JsonProperty("isAdmitted")
    var isAdmitted: Boolean = false
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: ApplicationEntity): ApplicationRs {
            return ApplicationRs(
                entity.id,
                entity.fullName,
                entity.email,
                ProgramRs.fromEntity(entity.program),
                entity.submissionDate,
                entity.isAdmitted
            )
        }

        @JvmStatic
        fun fromEntityList(entities: List<ApplicationEntity>): List<ApplicationRs> {
            return StreamSupport.stream(entities.spliterator(), false)
                .map(ApplicationRs::fromEntity)
                .toList();
        }
    }
}
