package lumina.emart.controllers

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lumina.emart.dtos.SignUpDto
import lumina.emart.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpDto: SignUpDto) = userService.singUp(signUpDto)

    @GetMapping("/verify")
    fun verifyEmail(httpServletResponse:HttpServletResponse, token: String, callbackUrl:String) {
        userService.verifyEmail(token)
        httpServletResponse.status = HttpServletResponse.SC_FOUND
        httpServletResponse.setHeader("Location", callbackUrl)
    }
}