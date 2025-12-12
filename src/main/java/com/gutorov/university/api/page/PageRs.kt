package com.gutorov.university.api.page

import org.springframework.data.domain.Page

data class PageRs<T>(
    var items: List<T>,
    var itemsCount: Int,
    var currentPage: Int,
    var currentSize: Int,
    var totalPages: Int,
    var totalItems: Long,
    var isFirstPage: Boolean,
    var isLastPage: Boolean,
    var hasNextPage: Boolean,
    var hasPreviousPage: Boolean,
) {
    companion object {
        @JvmStatic
        fun <E, D> Page<E>.from(mapper: (E) -> D): PageRs<D> =
            PageRs(
                items = content.map(mapper).toList(),
                itemsCount = numberOfElements,
                currentPage = number + 1,
                currentSize = size,
                totalPages = totalPages,
                totalItems = totalElements,
                isFirstPage = isFirst,
                isLastPage = isLast,
                hasNextPage = hasNext(),
                hasPreviousPage = hasPrevious()
            )
    }
}