package lumina.emart.dtos

data class CreateUserDto(
    val name: String,
    val email: String,
    val password: String,
)