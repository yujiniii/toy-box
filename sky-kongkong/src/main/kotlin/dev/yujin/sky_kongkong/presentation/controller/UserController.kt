package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.domain.security.CustomUserDetails
import dev.yujin.sky_kongkong.presentation.dto.UserCreationDto
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.dto.UserLoginDto
import dev.yujin.sky_kongkong.presentation.dto.UserTimeDto
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.apache.catalina.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
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
        @AuthenticationPrincipal user: CustomUserDetails,
        @RequestBody body: UserTimeDto
    ): String {
        return userService.addTimes(user.getUserId(), body)
    }

    @GetMapping("/me")
    fun me(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): Long {
        return user.getUserId()
    }

    @PostMapping("/withdraw")
    fun withdraw(
        @AuthenticationPrincipal user: CustomUserDetails,
        ): String {
        return userService.withdraw(user.getUserId())
    }


    @PostMapping("/logout")
    fun logout() {
        // @todo implement
    }
}



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