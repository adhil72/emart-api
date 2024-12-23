package lumina.emart.dtos

data class CreateProductDto(
    val name: String,
    val category: String,
    val price: Double,
    val stockQuantity: Int
)

data class UpdateProductDto(
    val id: String,
    val name: String?=null,
    val category: String?=null,
    val price: Double?=null,
    val stockQuantity: Int?=null
)