package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.domain.security.CustomUserDetails
import dev.yujin.sky_kongkong.presentation.dto.*
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/add")
    fun addTime(
        @AuthenticationPrincipal user: CustomUserDetails,
        @ModelAttribute body: UserTimeUpdateDto
    ): String {
        return userService.addTimes(user.getUserId(), body.toMinutes())
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