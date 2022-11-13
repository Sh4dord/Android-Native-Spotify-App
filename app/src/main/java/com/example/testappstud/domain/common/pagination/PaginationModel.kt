package com.example.testappstud.domain.common.pagination

/** [PaginationModel] is used to wrap a Paginated entity **/

class PaginationModel<T>(
    var href: String = "",
    var items: List<T>,
    var limit: Int = 20,
    var total: Int = 0,
) {
}