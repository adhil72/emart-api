package lumina.emart.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import lumina.emart.dtos.JwtObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    private val secretKey = Keys.hmacShaKeyFor(System.getenv("JWT_TOKEN").toByteArray())

    fun generateToken(jwtObject: JwtObject): String {
        return Jwts.builder()
            .setSubject(jwtObject.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + (1000 * 60 * 60*24*30.0).toInt()))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): JwtObject {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        return JwtObject(claims.subject)
    }
}
