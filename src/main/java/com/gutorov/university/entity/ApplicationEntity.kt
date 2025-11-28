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
    private var _program: ProgramEntity?
) : BaseEntity() {
    constructor() : this("", "", LocalDateTime.MIN, false, null)

    var program: ProgramEntity?
        get() = _program
        set(value) {
            if (this._program != value) {
                this._program?.removeApplication(this)
                this._program = value
                value?.addApplication(this)
            }
        }
}
