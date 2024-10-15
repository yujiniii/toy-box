package dev.yujin.sky_kongkong.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController {

    @GetMapping("/test")
    fun sayHello(): String {
        return "Hello"
    }
}