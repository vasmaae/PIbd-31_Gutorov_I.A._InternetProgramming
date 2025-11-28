package com.gutorov.university.entity

import jakarta.persistence.*

@Entity
@Table(name = "programs")
class ProgramEntity(
    @Column(nullable = false, length = 512) var name: String
) : BaseEntity() {
    @OneToMany(mappedBy = "_program", cascade = [CascadeType.ALL], orphanRemoval = true)
    var applications: MutableSet<ApplicationEntity> = hashSetOf()
        private set

    fun addApplication(application: ApplicationEntity) = applications.add(application.also { it.program = this })
    fun removeApplication(application: ApplicationEntity) = applications.remove(application.also { it.program = null })
}
