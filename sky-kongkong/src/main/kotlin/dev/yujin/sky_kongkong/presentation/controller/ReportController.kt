package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.dto.ReportCreationDto
import dev.yujin.sky_kongkong.presentation.dto.ReportDto
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.service.ReportService
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
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
        @RequestHeader header: RequestHeader,
        @RequestBody body: ReportCreationDto
    ){
        val userId: Long = 1 // @todo change userId from session or cookie
        reportService.createNewReport(userId, body)
    }

    @GetMapping("/{reportId}")
    fun getReportById(
        @RequestHeader header: RequestHeader,
        @RequestParam reportId: String
    ): ReportDto {
        val userId: Long = 1 // @todo change userId from session or cookie
        return reportService.getReportById(userId, reportId.toLong())
    }



}