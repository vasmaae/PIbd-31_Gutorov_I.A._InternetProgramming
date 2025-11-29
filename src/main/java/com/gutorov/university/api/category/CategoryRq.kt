package com.gutorov.university.api.category

import jakarta.validation.constraints.NotBlank

class CategoryRq(var name: @NotBlank String? = null)
