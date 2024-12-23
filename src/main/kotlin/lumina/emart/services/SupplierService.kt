package lumina.emart.services

import lumina.emart.dtos.CreateSupplierDto
import lumina.emart.dtos.Response
import lumina.emart.dtos.UpdateSupplierDto
import lumina.emart.entities.SupplierEntity
import lumina.emart.repositories.SupplierRepository
import lumina.emart.utils.FetchParams
import lumina.emart.utils.fetchData
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class SupplierService(
    private val supplierRepository: SupplierRepository,
    private val mongoTemplate: MongoTemplate
) {

    fun createSupplier(createSupplierDto: CreateSupplierDto): Response {
        val supplier = SupplierEntity(
            name = createSupplierDto.name,
            contact = createSupplierDto.contact,
            address = createSupplierDto.address,
            email = createSupplierDto.email,
            phone = createSupplierDto.phone
        )
        supplierRepository.save(supplier)
        return Response("Supplier created successfully", supplier)
    }

    fun fetchSuppliers(fetchParams: FetchParams): Response {
        val data = fetchData(supplierRepository,mongoTemplate, fetchParams, SupplierEntity::class.java)
        return Response("Suppliers fetched successfully", data)
    }

    fun updateSupplier(id: String, updateSupplierDto: UpdateSupplierDto): Response {
        val supplier = supplierRepository.findById(id).orElse(null) ?: return Response("Supplier not found", null)

        updateSupplierDto.name?.let { supplier.name = it }
        updateSupplierDto.contact?.let { supplier.contact = it }
        updateSupplierDto.address?.let { supplier.address = it }
        updateSupplierDto.email?.let { supplier.email = it }
        updateSupplierDto.phone?.let { supplier.phone = it }

        supplierRepository.save(supplier)
        return Response("Supplier updated successfully", supplier)
    }

    fun deleteSupplier(id: String): Response {
        supplierRepository.deleteById(id)
        return Response("Supplier deleted successfully", null)
    }
}
