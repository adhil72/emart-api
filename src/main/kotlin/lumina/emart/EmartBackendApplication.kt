package lumina.emart

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmartBackendApplication {
    private val logger = LoggerFactory.getLogger(EmartBackendApplication::class.java)
}

fun main(args: Array<String>) {
    runApplication<EmartBackendApplication>(*args)
}
