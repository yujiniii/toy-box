package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    // admin @todo 컨트롤러 분리
    @GetMapping("")
    fun getAllUsers(): List<UserDto> {
        return userService.getUsers()
    }

    // admin  @todo 컨트롤러 분리
    @GetMapping("/{userId}")
    fun getUserById() {
        // @todo implement
    }

    @PostMapping("/login")
    fun register() {
        // @todo implement
    }

    @PostMapping("/add")
    fun addTime() {
        // @todo implement
    }
}