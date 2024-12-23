package lumina.emart.dtos

data class CreateCategoryDto(
    val name: String
)

data class UpdateCategoryDto(
    val id: String,
    val name: String?=null
)