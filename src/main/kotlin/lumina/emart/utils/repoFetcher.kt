package lumina.emart.utils

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository

fun <T, ID> fetchRepo(repository: MongoRepository<T, ID>, page: Int, limit: Int, sortKey: String? = null, sortOrder: String = "asc"): MutableMap<String, Any> {
    val sort = if (sortKey != null) {
        if (sortOrder.equals("desc", ignoreCase = true)) Sort.by(sortKey).descending() else Sort.by(sortKey).ascending()
    } else null
    val pageable = if (sort != null) {
        org.springframework.data.domain.PageRequest.of(page - 1, limit, sort)
    } else {
        org.springframework.data.domain.PageRequest.of(page - 1, limit)
    }
    val data = repository.findAll(pageable)
    val map = mutableMapOf<String, Any>()
    map["data"] = data.content
    map["page"] = mutableMapOf("current" to data.pageable.pageNumber + 1, "total" to data.totalPages, "totalItems" to data.totalElements)
    return map
}