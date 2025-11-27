package com.gutorov.university.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    open var id: UUID = UUID.randomUUID()
        protected set

    protected constructor()
}