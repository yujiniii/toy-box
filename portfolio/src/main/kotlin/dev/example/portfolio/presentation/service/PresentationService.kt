package dev.example.portfolio.presentation.service

import dev.example.portfolio.presentation.dto.IntroductionDTO
import dev.example.portfolio.presentation.dto.LinkDTO
import dev.example.portfolio.presentation.dto.ProjectDTO
import dev.example.portfolio.presentation.dto.ResumeDTO
import dev.example.portfolio.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    @Transactional(readOnly = true)
    fun getIntroductions(): List<IntroductionDTO> {
        val introductions = presentationRepository.getActiveIntroduction()
        return introductions.map { IntroductionDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getLinks(): List<LinkDTO> {
        val links = presentationRepository.getActiveLink()
        return links.map { LinkDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getResume(): ResumeDTO {
        val experiences = presentationRepository.getActiveExperience()
        val achievements = presentationRepository.getActiveAchievement()
        val skills = presentationRepository.getActiveSkills()
        return ResumeDTO(
            experiences = experiences,
            achievements = achievements,
            skills = skills
        )
    }

    @Transactional(readOnly = true)
    fun getProjects(): List<ProjectDTO> {
        val projects = presentationRepository.getActiveProject()
        return projects.map { ProjectDTO(it) }
    }
}