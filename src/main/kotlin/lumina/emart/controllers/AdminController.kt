package lumina.emart.controllers

import lumina.emart.dtos.CreateUserDto
import lumina.emart.dtos.Response
import lumina.emart.services.AdminService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(private val adminService: AdminService) {

    @PostMapping("/user/create")
    fun createUser(@RequestBody createUserDto: CreateUserDto):Response = adminService.createUser(createUserDto)
}