package dev.yujin.sky_kongkong.presentation.controller

import dev.yujin.sky_kongkong.presentation.service.SeatService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

data class Seat(val number: Int, val isActive: Boolean)


@Controller
class PresentationViewController(
    private val seatService: SeatService
) {

    @GetMapping("/test")
    fun sayHello(): String {
        return "Hello"
    }

    @GetMapping("")
    fun view(): String {
        return "presentation/index"
    }

    @GetMapping("/seat")
    fun desk(model: Model): String {
        val seats = seatService.getSeatInfo()
        val seatCount = seatService.getRemainSeatCount()

        model.addAttribute("seats", seats)
        model.addAttribute("seatCount", seatCount)

        return "presentaion/seat"
    }

    @GetMapping("/login")
    fun login(): String {
        return "presentation/login"
    }


    @GetMapping("/register")
    fun register(): String {
        return "presentation/register"
    }
}
