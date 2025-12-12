package com.gutorov.university.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "applications")
open class ApplicationEntity(
    @Column(name = "full_name", nullable = false, length = 512) var fullName: String,
    @Column(nullable = false, unique = true) var email: String,
    @Column(name = "submission_date", nullable = false) var submissionDate: LocalDateTime,
    @Column(name = "is_admitted", nullable = false) var isAdmitted: Boolean,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "program_id", nullable = false)
    var program: ProgramEntity?
) : BaseEntity() {
    constructor() : this("", "", LocalDateTime.MIN, false, null)

    fun changeProgram(program: ProgramEntity?) {
        if (this.program != program) {
            this.program?.removeApplication(this)
            this.program = program
            program?.addApplication(this)
        }
    }
}
