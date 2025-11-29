package com.gutorov.university.api.author

import jakarta.validation.constraints.NotBlank

class AuthorRq(var name: @NotBlank String? = null)
