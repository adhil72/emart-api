package lumina.emart.dtos

data class CreateCategoryDto(
    val name: String
)

data class UpdateCategoryDto(
    val name: String?=null
)