package lumina.emart.dtos

data class CreateUserDto(
    val email: String,
    val name: String,
    val role: String
)