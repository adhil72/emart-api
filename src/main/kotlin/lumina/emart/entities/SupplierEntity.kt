package lumina.emart.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "suppliers")
data class SupplierEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var contact: String,
    var address: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var createdAt: String = java.time.LocalDateTime.now().toString(),
    var updatedAt: String = java.time.LocalDateTime.now().toString()
)
