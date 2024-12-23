package lumina.emart.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "categories")
class CategoryEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var name: String
)