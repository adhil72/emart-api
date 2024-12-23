package lumina.emart.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date
import java.util.UUID

@Document(collection = "tokens")
data class TokenEntity(
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val expiryDate: Date = Date(System.currentTimeMillis() + 1000 * 60 * 5)
)