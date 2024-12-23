package lumina.emart.repositories

import lumina.emart.entities.CategoryEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository:MongoRepository<CategoryEntity,String> {
    fun findByName(name:String):CategoryEntity?
}