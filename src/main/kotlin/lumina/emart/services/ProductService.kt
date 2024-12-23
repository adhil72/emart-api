package lumina.emart.services

import lumina.emart.dtos.CreateProductDto
import lumina.emart.dtos.Response
import lumina.emart.dtos.UpdateProductDto
import lumina.emart.entities.ProductEntity
import lumina.emart.repositories.ProductRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
){
    fun createProduct(createProductDto: CreateProductDto):Response{
        val product = ProductEntity(
            name = createProductDto.name,
            category = createProductDto.category,
            price = createProductDto.price,
            stockQuantity = createProductDto.stockQuantity
        )
        productRepository.save(product)
        return Response("Product created successfully", null)
    }

    fun updateProduct(updateProductDto: UpdateProductDto):Response{
        val product = productRepository.findById(updateProductDto.id).orElse(null) ?: return Response("Product not found", null)
        updateProductDto.name?.let { product.name = it }
        updateProductDto.category?.let { product.category = it }
        updateProductDto.price?.let { product.price = it }
        updateProductDto.stockQuantity?.let { product.stockQuantity = it }
        productRepository.save(product)
        return Response("Product updated successfully", null)
    }

    fun deleteProduct(id: String):Response{
        productRepository.deleteById(id)
        return Response("Product deleted successfully", null)
    }

    fun fetchProducts(page:Int, limit: Int):Response{
        val products = productRepository.findAll(Pageable.ofSize(limit).withPage(page))
        return Response("Products fetched successfully", products)
    }
}