package com.gutorov.university.repository

import com.gutorov.university.entity.ProgramEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProgramRepository : JpaRepository<ProgramEntity, UUID>
