package lumina.emart.services

import lumina.emart.dtos.JwtObject
import lumina.emart.dtos.Response
import lumina.emart.dtos.SignUpDto
import lumina.emart.entities.TokenEntity
import lumina.emart.entities.UserEntity
import lumina.emart.exceptions.ExpectedException
import lumina.emart.extras.Config
import lumina.emart.extras.Roles
import lumina.emart.repositories.TokenRepository
import lumina.emart.repositories.UserRepository
import lumina.emart.templates.welcomeUserTemplate
import lumina.emart.utils.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Date

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val tokenRepository: TokenRepository,
    private val emailService: EmailService
) {

    fun singUp(signUpDto: SignUpDto): Response {
        val exists = userRepository.findByEmail(signUpDto.email) != null
        if (exists) throw ExpectedException("User already exists", 400)

        val user = UserEntity(
            role = Roles.ADMIN,
            email = signUpDto.email,
            name = signUpDto.name,
            password = passwordEncoder.encode(signUpDto.password)
        )
        userRepository.save(user)

        val verificationToken = tokenRepository.save(TokenEntity(uid = user.id)).id
        val verificationUrl = "${Config.BASE_URL}/user/verify?token=$verificationToken"
        emailService.sendEmail(
            to = user.email,
            subject = "Verify your email",
            body = welcomeUserTemplate(user.name, user.email, verificationUrl)
        )

        val token = jwtUtil.generateToken(JwtObject(user.id, user.role))

        return Response(
            message = "User created successfully",
            data = mapOf("token" to token)
        )
    }

    fun verifyEmail(token: String): Boolean {
        val tokenEntity = tokenRepository.findById(token).orElseThrow { ExpectedException("Invalid token", 400) }
        if (tokenEntity.expiryDate.before(Date())) throw ExpectedException("Token expired", 400)
        val user = userRepository.findById(tokenEntity.uid).get()
        user.verified = true
        userRepository.save(user)
        tokenRepository.delete(tokenEntity)
        return true
    }
}