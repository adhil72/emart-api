package lumina.emart.services

import lumina.emart.dtos.CreateUserDto
import lumina.emart.dtos.JwtObject
import lumina.emart.dtos.Response
import lumina.emart.entities.UserEntity
import lumina.emart.exceptions.ExpectedException
import lumina.emart.extras.Roles
import lumina.emart.repositories.UserRepository
import lumina.emart.utils.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
    ) {
    fun singUp(createUserDto: CreateUserDto):Response{
        val exists = userRepository.findByEmail(createUserDto.email) != null
        if(exists) throw ExpectedException("User already exists", 400)

        val user = UserEntity(
            role = Roles.ADMIN,
            email = createUserDto.email,
            name = createUserDto.name,
            password = passwordEncoder.encode(createUserDto.password)
        )
        userRepository.save(user)

        val token = jwtUtil.generateToken(JwtObject(
            user.id, user.role
        ))

        return Response(
            message = "User created successfully",
            data = mapOf("token" to token)
        )
    }
}