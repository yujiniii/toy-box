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
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(
        @ModelAttribute body: UserCreationDto
    ): String {
        userService.register(body)

        return "redirect:/login"
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: UserLoginDto
    ): UserDto {
        return userService.login(body)
    }
}
