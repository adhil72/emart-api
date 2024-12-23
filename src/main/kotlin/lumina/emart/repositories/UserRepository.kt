package lumina.emart.repositories

import lumina.emart.entities.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository:MongoRepository<UserEntity,String>{
    fun findByEmail(email:String):UserEntity?
    fun findByUsername(userName:String):UserEntity?
}