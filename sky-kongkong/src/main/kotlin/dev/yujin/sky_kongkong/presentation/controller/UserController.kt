package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.dto.UserCreationDto
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.dto.UserLoginDto
import dev.yujin.sky_kongkong.presentation.dto.UserTimeDto
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.apache.catalina.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {

//    // admin @todo 컨트롤러 분리
//    @GetMapping("")
//    fun getAllUsers(): List<UserDto> {
//        return userService.getUsers()
//    }
//
//    // admin  @todo 컨트롤러 분리
//    @GetMapping("/{userId}")
//    fun getUserById() {
//        // @todo implement
//    }

    @PostMapping("/register")
    fun register(
        @RequestBody body: UserCreationDto
    ): UserDto {
        return userService.register(body)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: UserLoginDto
    ): UserDto {
        return userService.login(body)
    }

    @PostMapping("/add")
    fun addTime(
        @RequestBody body: UserTimeDto
    ): String {
        val userId: Long = 1 // @todo change userId from session or cookie
        return userService.addTimes(userId, body)
    }

    @PostMapping("/withdraw")
    fun withdraw(): String {
        val userId: Long = 1 // @todo change userId from session or cookie
        return userService.withdraw(userId)
    }


    @PostMapping("/logout")
    fun logout() {
        // @todo implement
    }
}