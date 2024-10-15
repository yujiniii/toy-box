package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/report")
class ReportController(
    private val userService: UserService
) {

    // admin @todo 컨트롤러 분리
    @GetMapping("")
    fun getUserCafeInfo() {
        // @todo implement
    }

    @GetMapping("/{reportId}}")
    fun getReportById() {
        // @todo implement
    }

    // admin @todo 컨트롤러 분리
    @PutMapping("/{reportId}}")
    fun editReportById() {
        // @todo implement
    }

}