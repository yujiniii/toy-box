package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.dto.*
import dev.yujin.sky_kongkong.presentation.service.SeatService
import dev.yujin.sky_kongkong.presentation.service.UsageService
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafe")
class CafeController(
    private val usageService: UsageService,
    private val seatService: SeatService
) {

    @GetMapping("")
    fun getUserCafeInfo(): UsageDto {
        val userId: Long = 1 // @todo change userId from session or cookie
        return usageService.getUserUsage(userId)
    }


    @PostMapping("/enter")
    fun enterCafe(
        @RequestBody body: UsageCreationDto
    ): String {
        val userId: Long = 1 // @todo change userId from session or cookie
        return usageService.enterCafe(userId, body)
    }

    @PostMapping("/leave/{usageId}")
    fun leaveCafe(
        @RequestParam usageId: String,
    ): String {
        val userId: Long = 1 // @todo change userId from session or cookie
        return usageService.leaveCafe(userId, usageId.toLong())
    }

    @GetMapping("/seat-info")
    fun getSeatInfo(): List<SeatDto> {
        return seatService.getSeatInfo()
    }
}