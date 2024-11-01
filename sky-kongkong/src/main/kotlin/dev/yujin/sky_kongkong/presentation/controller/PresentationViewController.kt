package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.domain.security.CustomUserDetails
import dev.yujin.sky_kongkong.presentation.dto.ReportCreationDto
import dev.yujin.sky_kongkong.presentation.dto.UserCreationDto
import dev.yujin.sky_kongkong.presentation.dto.UserTimeUpdateDto
import dev.yujin.sky_kongkong.presentation.service.SeatService
import dev.yujin.sky_kongkong.presentation.service.UsageService
import dev.yujin.sky_kongkong.presentation.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

data class Seat(val number: Int, val isActive: Boolean)


@Controller
class PresentationViewController(
    private val seatService: SeatService,
    private val usageService: UsageService,
    private val userService: UserService
) {

    @GetMapping("/test")
    fun sayHello(): String {
        return "Hello"
    }

    @GetMapping("")
    fun view(
        model: Model,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): String {
        model.addAttribute("username", user.getDisplayName())
        model.addAttribute("userId", user.getUserId())
        return "presentation/index"
    }

    @GetMapping("/seat")
    fun desk(
        model: Model,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): String {
        val seats = seatService.getSeatInfo()
        val seatCount = seatService.getRemainSeatCount()
        val userReservedSeat = seatService.getUserReservedSeat(user.getUserId())
        val hasCheckedIn = userReservedSeat != null

        model.addAttribute("username", user.getDisplayName())
        model.addAttribute("userId", user.getUserId())

        model.addAttribute("userReservedSeat", userReservedSeat)
        model.addAttribute("seats", seats)
        model.addAttribute("seatCount", seatCount)
        model.addAttribute("hasCheckedIn", hasCheckedIn)

        return "presentation/seat"
    }

    @GetMapping("/login")
    fun login(): String {
        return "presentation/login"
    }


    @GetMapping("/register")
    fun register(model: Model): String {
        model.addAttribute("body", UserCreationDto())
        return "presentation/register"
    }

    @GetMapping("/report")
    fun register(
        model: Model,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): String {
        model.addAttribute("username", user.getDisplayName())
        model.addAttribute("userId", user.getUserId())

        model.addAttribute("body", ReportCreationDto())
        return "presentation/report"
    }

    @GetMapping("/add-time")
    fun addTime(
        model: Model,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): String {
        model.addAttribute("username", user.getDisplayName())
        model.addAttribute("userId", user.getUserId())

        model.addAttribute("body", UserTimeUpdateDto())
        return "presentation/add-time"
    }

    @GetMapping("/my-page")
    fun myPage(
        model: Model,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): String {
        val userId = user.getUserId()
        val usage = usageService.getUserUsage(userId)
        val user = userService.getUserById(userId)
        val remainMinutes = userService.getRemainMinutes(userId)

        model.addAttribute("user", user)
        model.addAttribute("myUsage", usage)
        model.addAttribute("remainMinutes", remainMinutes)
        println("reM $remainMinutes")
        println("reM $usage")

        return "presentation/my-page"
    }
}
