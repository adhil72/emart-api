package lumina.emart.services

import lumina.emart.dtos.CreateUserDto
import lumina.emart.dtos.Response
import lumina.emart.entities.UserEntity
import lumina.emart.exceptions.ExpectedException
import lumina.emart.repositories.UserRepository
import lumina.emart.templates.userOnboardingTemplate
import lumina.emart.utils.generatePassword
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService
) {
    fun createUser(createUserDto: CreateUserDto):Response {
        if (userRepository.existsByEmail(createUserDto.email)) throw ExpectedException("Email already exists", 400)
        val generatedPassword = generatePassword()
        val user = UserEntity(
            name = createUserDto.name,
            email = createUserDto.email,
            password = passwordEncoder.encode(generatedPassword),
            role = createUserDto.role
        )
        userRepository.save(user)
        emailService.sendEmail(
            to = user.email,
            subject = "Emart Account Created",
            body = userOnboardingTemplate(
                user.name,
                user.email,
                generatedPassword,
                "http://localhost:3000"
            )
        )
        return Response("User created successfully", null)
    }
}