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

@Controller
@RequestMapping("/api/report")
class ReportController(
    private val reportService: ReportService
) {

//    // admin @todo 컨트롤러 분리
//    @GetMapping("")
//    fun getUserCafeInfo() {
//        // @todo implement
//    }
//
//    // admin @todo 컨트롤러 분리
//    @PutMapping("/{reportId}")
//    fun editReportById() {
//        // @todo implement
//    }

    @PostMapping("")
    fun createNewReport(
        @AuthenticationPrincipal user: CustomUserDetails,
        @ModelAttribute body: ReportCreationDto
    ): String{
        reportService.createNewReport(user.getUserId(), body)

        return "redirect:/"

    }

    @GetMapping("/{reportId}")
    fun getReportById(
        @AuthenticationPrincipal user: CustomUserDetails,
        @RequestParam reportId: String
    ): ReportDto {
        return reportService.getReportById(user.getUserId(), reportId.toLong())
    }



}