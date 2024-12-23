package lumina.emart.utils

import lumina.emart.repositories.SupplierRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.support.PageableExecutionUtils

data class FetchParams(
    var page: Int = 1,
    var limit: Int = 10,
    var sortKey: String? = null,
    var sortOrder: String = "desc",
    var searchKey: String? = null,
    var searchValue: String? = null
)

fun <T, ID> fetchData(
    repository: MongoRepository<T, ID>,
    mongoTemplate: MongoTemplate, // Add MongoTemplate as a parameter
    fetchParams: FetchParams,
    entityClass: Class<T> // Add entity class for querying
): Map<String, Any?> {
    val page: Int = (fetchParams.page - 1).coerceAtLeast(0)
    val limit: Int = fetchParams.limit.coerceAtLeast(1)

    val sort: Sort = if (!fetchParams.sortKey.isNullOrEmpty()) {
        if (fetchParams.sortOrder.equals("asc", ignoreCase = true)) {
            Sort.by(Sort.Direction.ASC, fetchParams.sortKey!!)
        } else {
            Sort.by(Sort.Direction.DESC, fetchParams.sortKey!!)
        }
    } else {
        Sort.unsorted()
    }

    val pageable: Pageable = PageRequest.of(page, limit, sort)

    // Build the query with search criteria
    val query = Query().with(pageable)
    if (!fetchParams.searchKey.isNullOrEmpty() && !fetchParams.searchValue.isNullOrEmpty()) {
        query.addCriteria(Criteria.where(fetchParams.searchKey!!).regex(fetchParams.searchValue!!, "i")) // Case-insensitive regex search
    }

    // Use MongoTemplate to execute the query and count the total
    val data = mongoTemplate.find(query, entityClass)
    val countQuery = query.limit(0).skip(0) // Create a new query for counting without limits
    val total = mongoTemplate.count(countQuery, entityClass)

    // Use PageableExecutionUtils to create a Page
    val pageData = PageableExecutionUtils.getPage(data, pageable) { total }

    return mapOf(
        "data" to pageData.content,
        "page" to mapOf(
            "current" to fetchParams.page,
            "total" to pageData.totalPages,
            "totalItems" to pageData.totalElements
        )
    )
}