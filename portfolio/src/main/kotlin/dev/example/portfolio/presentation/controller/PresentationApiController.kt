package dev.example.portfolio.presentation.controller

import dev.example.portfolio.presentation.dto.IntroductionDTO
import dev.example.portfolio.presentation.dto.LinkDTO
import dev.example.portfolio.presentation.dto.ProjectDTO
import dev.example.portfolio.presentation.dto.ResumeDTO
import dev.example.portfolio.presentation.service.PresentationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationApiController(
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    // == @RequestMapping(method = [RequestMethod.GET], name = "/test")
    fun test(): String {
        return "OK"  // return body 에 string 을 넣어 응답
    }

    @GetMapping("/v1/introductions")
    fun getIntroductions(): List<IntroductionDTO> {
        return presentationService.getIntroductions()
    }

    @GetMapping("/v1/links")
    fun getLinks(): List<LinkDTO> {
        return presentationService.getLinks()
    }

    @GetMapping("/v1/resume")
    fun getResume(): ResumeDTO {
        return presentationService.getResume()
    }

    @GetMapping("/v1/projects")
    fun getProjects(): List<ProjectDTO> {
        return presentationService.getProjects()
    }


}
