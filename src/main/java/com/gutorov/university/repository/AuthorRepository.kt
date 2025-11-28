package com.gutorov.university.repository

import com.gutorov.university.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AuthorRepository : JpaRepository<AuthorEntity, UUID>
