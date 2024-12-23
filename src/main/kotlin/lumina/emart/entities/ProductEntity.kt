package lumina.emart.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "products")
data class ProductEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var category: String,
    var stockQuantity: Int,
    var unitPrice: Double,
    var costPrice: Double,
    var reorderLevel: Int,
    var expiryDate: String? = null,
    var tags: List<String>? = null,
    var supplier: String? = null,
    var taxRate: Double? = null,
    var cessRate: Double? = null,
    var customDuty: Double? = null,
    var supplierName: String? = null,
    var supplierContact: String? = null,
    var createdAt: String = java.time.LocalDateTime.now().toString(),
    var updatedAt: String = java.time.LocalDateTime.now().toString()
)