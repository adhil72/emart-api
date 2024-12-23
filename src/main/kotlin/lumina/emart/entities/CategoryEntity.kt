package lumina.emart.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "categories")
class CategoryEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var createdAt: Date = Date(System.currentTimeMillis()),
    var updatedAt: Date = Date(System.currentTimeMillis())
)