package lumina.emart.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "products")
data class ProductEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var category: String,
    var price: Double,
    var stockQuantity: Int
)