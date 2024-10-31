package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.domain.security.CustomUserDetails
import dev.yujin.sky_kongkong.presentation.dto.*
import dev.yujin.sky_kongkong.presentation.service.SeatService
import dev.yujin.sky_kongkong.presentation.service.UsageService
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.data.jpa.repository.Query
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafe")
class CafeController(
    private val usageService: UsageService,
    private val seatService: SeatService
) {

    @GetMapping("")
    fun getUserCafeInfo(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): UsageDto {
        return usageService.getUserUsage(user.getUserId())
    }


    @PostMapping("/enter")
    fun enterCafe(
        @AuthenticationPrincipal user: CustomUserDetails,
        @RequestParam("seatId") seatId: Int
    ): String {
        usageService.enterCafe(user.getUserId(), seatId)

        return "ok"
    }

    @PostMapping("/leave")
    fun leaveCafe(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): String {
        usageService.leaveCafe(user.getUserId())
        return "ok"
    }

    @GetMapping("/seat-info")
    fun getSeatInfo(): List<SeatDto> {
        return seatService.getSeatInfo()
    }
}