package lumina.emart.dtos

data class CreateProductDto(
    val name: String,
    val category: String,
    val unitPrice: Double,
    val stockQuantity: Int,
    val reorderLevel: Int,
    val costPrice: Double? = null,
    val expiryDate: String? = null,
    val tags: List<String>? = null,
    val taxRate: Double? = null,
    val cessRate: Double? = null,
    val customDuty: Double? = null,
    val supplier: String? = null,
)

data class UpdateProductDto(
    val name: String? = null,
    val category: String? = null,
    val unitPrice: Double? = null,
    val costPrice: Double? = null,
    val stockQuantity: Int? = null,
    val reorderLevel: Int? = null,
    val expiryDate: String? = null,
    val tags: List<String>? = null,
    val supplier: String? = null,
    val taxRate: Double? = null,
    val cessRate: Double? = null,
    val customDuty: Double? = null,
)
