package com.gutorov.university.api.program

import jakarta.validation.constraints.NotBlank

data class ProgramRq(var name: @NotBlank String? = null)