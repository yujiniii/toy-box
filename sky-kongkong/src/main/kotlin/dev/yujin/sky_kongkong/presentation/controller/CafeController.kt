package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.domain.security.CustomUserDetails
import dev.yujin.sky_kongkong.presentation.dto.*
import dev.yujin.sky_kongkong.presentation.service.SeatService
import dev.yujin.sky_kongkong.presentation.service.UsageService
import dev.yujin.sky_kongkong.presentation.service.UserService
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
        @ModelAttribute body: UsageCreationDto
    ): String {
        return usageService.enterCafe(user.getUserId(), body)
    }

    @PostMapping("/leave/{usageId}")
    fun leaveCafe(
        @AuthenticationPrincipal user: CustomUserDetails,
        @RequestParam usageId: String,
    ): String {
        return usageService.leaveCafe(user.getUserId(), usageId.toLong())
    }

    @GetMapping("/seat-info")
    fun getSeatInfo(): List<SeatDto> {
        return seatService.getSeatInfo()
    }
}