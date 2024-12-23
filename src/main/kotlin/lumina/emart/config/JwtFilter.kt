package lumina.emart.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lumina.emart.extras.Roles
import lumina.emart.repositories.UserRepository
import lumina.emart.utils.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
class JwtFilter(private val jwtUtil: JwtUtil, private val userRepository: UserRepository) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            val jwtObject = jwtUtil.validateToken(token)
            if (jwtObject.role == Roles.ADMIN) {
                if (userRepository.findByIdAndVerified(jwtObject.id, true) == null) {
                    response.sendError(401, "User not verified")
                    return
                }
            }
            val authentication = UsernamePasswordAuthenticationToken(
                jwtObject.id,
                null,
                Collections.singleton(SimpleGrantedAuthority(jwtObject.role))
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}