package dev.example.portfolio.presentation.controller

import dev.example.portfolio.domain.constant.SkillType
import dev.example.portfolio.presentation.service.PresentationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

// restController 와 다름
// 서버사이드렌더링(SSR)
@Controller
class PresentationViewController(
    private val presentationService: PresentationService
) {

    @GetMapping("/test")
    fun test(): String {
        return "test" // 동일한 HTML 이름을 찾아서 return
    }


    @GetMapping("/")
    fun index(model: Model): String {
        val introductions = presentationService.getIntroductions()
        val links = presentationService.getLinks()
        model.addAttribute("introductions", introductions)
        model.addAttribute("links", links)
        return "presentation/index"
    }

    @GetMapping("/resume")
    fun resume(model: Model): String {
        val resume = presentationService.getResume()
        model.addAttribute("resume", resume)
        model.addAttribute("skillTypes", SkillType.values())
        return "presentation/resume"
    }


    @GetMapping("/projects")
    fun projects(model: Model): String {
        val projects = presentationService.getProjects()
        model.addAttribute("projects", projects)
        return "presentation/projects"
    }
}
