package lumina.emart.dtos

data class SignUpDto(
    val name:String,
    val email:String,
    val password:String,
)

data class SignInDto(
    val email:String,
    val password:String,
)