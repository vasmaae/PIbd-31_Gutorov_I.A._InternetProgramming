package com.gutorov.university.api.page

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class PageHelper {
    constructor()

    companion object {
        @JvmStatic
        fun toPageable(page: Int, size: Int): Pageable =
            PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "id"))
    }
}