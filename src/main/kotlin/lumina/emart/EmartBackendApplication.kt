package lumina.emart

import lumina.emart.repositories.UserRepository
import lumina.emart.services.EmailService
import lumina.emart.services.AdminService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class EmartBackendApplication {
    private val logger = LoggerFactory.getLogger(EmartBackendApplication::class.java)
}

fun main(args: Array<String>) {
    runApplication<EmartBackendApplication>(*args)
}
