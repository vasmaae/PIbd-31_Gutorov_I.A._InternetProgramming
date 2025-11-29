package com.gutorov.university.api.application

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.*

data class ApplicationRq(
    var fullName: @NotBlank String? = null,
    var email: @Email String? = null,
    var programId: @NotNull UUID? = null,
    var submissionDate: LocalDateTime? = null,
    @JsonProperty("isAdmitted")
    var isAdmitted: Boolean = false
) 
