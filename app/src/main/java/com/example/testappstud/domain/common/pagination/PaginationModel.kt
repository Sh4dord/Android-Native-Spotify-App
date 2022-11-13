package com.example.testappstud.domain.common.pagination

class PaginationModel<T>(
    var href: String = "",
    var items: List<T>,
    var limit: Int = 20,
    var total: Int = 0,
) {
}