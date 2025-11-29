package com.gutorov.university.api.news

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.*

class NewsRq(
    var title: @NotBlank String? = null,
    var content: @NotBlank String? = null,
    var categoryId: @NotNull UUID? = null,
    var authorId: @NotNull UUID? = null,
    var postDate: @NotNull LocalDateTime? = null
)
