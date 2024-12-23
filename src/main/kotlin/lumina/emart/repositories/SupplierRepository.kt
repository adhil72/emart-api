package lumina.emart.repositories

import lumina.emart.entities.SupplierEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SupplierRepository : MongoRepository<SupplierEntity, String>