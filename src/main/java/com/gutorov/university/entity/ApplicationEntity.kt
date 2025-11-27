package com.gutorov.university.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "applications")
class ApplicationEntity(
    @Column(nullable = false, length = 512) var fullName: String,
    @Column(nullable = false, unique = true) var email: String,
    @Column(nullable = false) var submissionDate: LocalDateTime,
    @Column(nullable = false) var isAdmitted: Boolean,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "program_id", nullable = false)
    var program: ProgramEntity?
) : BaseEntity() {
    fun setProgram(program: ProgramEntity?) {
        if (this.program != program) {
            this.program?.removeApplication(this)
            this.program = program
            program?.addApplication(this)
        }
    }
}
