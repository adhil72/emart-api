package lumina.emart.exceptions

import lumina.emart.dtos.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception):ResponseEntity<Response>{
        return ResponseEntity.status(503).body(Response(
            message = exception.message?:"Unexpected error occurred"
        ))
    }

    @ExceptionHandler(ExpectedException::class)
    fun handleExpectedException(exception: ExpectedException): ResponseEntity<Response> {
        return ResponseEntity.status(exception.code).body(Response(
            message = exception.message
        ))
    }
}