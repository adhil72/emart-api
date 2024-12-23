package lumina.emart.dtos

data class CreateSupplierDto(
    val name: String,
    val contact: String,
    val address: String? = null,
    val email: String? = null,
    val phone: String? = null
)

data class UpdateSupplierDto(
    val name: String? = null,
    val contact: String? = null,
    val address: String? = null,
    val email: String? = null,
    val phone: String? = null
)
