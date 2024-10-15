package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cafe")
class CafeController(
    private val userService: UserService
) {

    @GetMapping("")
    fun getUserCafeInfo() {
        // @todo implement
    }

    @PostMapping("/enter")
    fun enterCafe() {
        // @todo implement
    }

    @PostMapping("/leave/{usage_id}")
    fun leaveCafe() {
        // @todo implement
    }
}