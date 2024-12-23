package lumina.emart.services

import lumina.emart.dtos.CreateProductDto
import lumina.emart.dtos.Response
import lumina.emart.dtos.UpdateProductDto
import lumina.emart.entities.ProductEntity
import lumina.emart.repositories.ProductRepository
import lumina.emart.utils.FetchParams
import lumina.emart.utils.fetchData
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.util.Date

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun createProduct(createProductDto: CreateProductDto): Response {
        val product = ProductEntity(
            name = createProductDto.name,
            category = createProductDto.category,
            unitPrice = createProductDto.unitPrice,
            costPrice = createProductDto.costPrice ?: 0.0,
            stockQuantity = createProductDto.stockQuantity,
            reorderLevel = createProductDto.reorderLevel,
            expiryDate = createProductDto.expiryDate,
            tags = createProductDto.tags,
            supplier = createProductDto.supplier,
            taxRate = createProductDto.taxRate,
            cessRate = createProductDto.cessRate,
            customDuty = createProductDto.customDuty
        )
        productRepository.save(product)
        return Response("Product created successfully", product)
    }

    fun updateProduct(id: String, updateProductDto: UpdateProductDto): Response {
        val product = productRepository.findById(id).orElse(null)
            ?: return Response("Product not found", null)

        updateProductDto.name?.let { product.name = it }
        updateProductDto.category?.let { product.category = it }
        updateProductDto.unitPrice?.let { product.unitPrice = it }
        updateProductDto.costPrice?.let { product.costPrice = it }
        updateProductDto.stockQuantity?.let { product.stockQuantity = it }
        updateProductDto.reorderLevel?.let { product.reorderLevel = it }
        updateProductDto.expiryDate?.let { product.expiryDate = it }
        updateProductDto.tags?.let { product.tags = it }
        updateProductDto.supplier?.let { product.supplier = it }
        updateProductDto.taxRate?.let { product.taxRate = it }
        updateProductDto.cessRate?.let { product.cessRate = it }
        updateProductDto.customDuty?.let { product.customDuty = it }
        product.updatedAt = Date()

        productRepository.save(product)
        return Response("Product updated successfully", product)
    }

    fun deleteProduct(id: String): Response {
        if (!productRepository.existsById(id)) {
            return Response("Product not found", null)
        }
        productRepository.deleteById(id)
        return Response("Product deleted successfully", null)
    }

    fun fetchProducts(fetchParams: FetchParams): Response {
        val data = fetchData(productRepository,mongoTemplate, fetchParams, ProductEntity::class.java)
        return Response("Products fetched successfully", data)
    }
}