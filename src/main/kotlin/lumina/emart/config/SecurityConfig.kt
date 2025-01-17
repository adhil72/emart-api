package lumina.emart.config

import lumina.emart.extras.Roles
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtFilter: JwtFilter) {
    @Bean
    fun securityFilterChain(http:HttpSecurity):SecurityFilterChain{
        http.csrf{it.disable()}.authorizeHttpRequests {
            rolesList.forEach { role->
                if (role.value.contains("permitAll")) {
                    it.requestMatchers(role.key).permitAll()
                }else{
                    it.requestMatchers(role.key).hasAnyAuthority(*role.value.toTypedArray())
                }
            }
        }.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }
}