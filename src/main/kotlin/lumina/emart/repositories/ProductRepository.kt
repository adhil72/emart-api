package lumina.emart.repositories

import lumina.emart.entities.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<ProductEntity, String> {
    fun findByName(name: String, pageable: Pageable): Page<ProductEntity>
    fun findByCategory(category: String, pageable: Pageable): Page<ProductEntity>
    fun findByPrice(price: Double, pageable: Pageable): Page<ProductEntity>
    fun findByStockQuantity(stockQuantity: Int, pageable: Pageable): Page<ProductEntity>
}