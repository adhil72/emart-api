package lumina.emart.repositories

import lumina.emart.entities.TokenEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface TokenRepository:MongoRepository<TokenEntity, String>