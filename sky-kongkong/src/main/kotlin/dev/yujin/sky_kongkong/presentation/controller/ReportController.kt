package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.domain.security.CustomUserDetails
import dev.yujin.sky_kongkong.presentation.dto.ReportCreationDto
import dev.yujin.sky_kongkong.presentation.dto.ReportDto
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.service.ReportService
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/report")
class ReportController(
    private val reportService: ReportService
) {
    @PostMapping("")
    fun createNewReport(
        @AuthenticationPrincipal user: CustomUserDetails,
        @RequestBody body: ReportCreationDto
    ): String {
        reportService.createNewReport(user.getUserId(), body)

        return "ok"
    }
}