package lumina.emart.repositories

import lumina.emart.entities.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository:MongoRepository<UserEntity,String>{
    fun findByEmail(email:String):UserEntity?
    fun findByIdAndVerified(id:String,verified:Boolean):UserEntity?
    fun existsByEmail(email:String):Boolean
}