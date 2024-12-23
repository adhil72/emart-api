package lumina.emart.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date
import java.util.UUID


@Document(collection = "users")
data class UserEntity(
    @Id
    val id:String = UUID.randomUUID().toString(),
    var name:String,
    var email:String,
    var password:String,
    var role:String,
    var verified:Boolean = false,
    var blocked:Boolean = false,
    val createdAt:Date = Date(),
    var updatedAt:Date = Date()
)